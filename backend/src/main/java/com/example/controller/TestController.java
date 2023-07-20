package com.example.controller;
import org.springframework.beans.factory.annotation.*;

import com.google.auth.oauth2.GoogleCredentials;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredentials;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.healthcare.v1beta1.CloudHealthcare;
import com.google.api.services.healthcare.v1beta1.model.ListFhirStoresResponse;
import com.google.api.services.healthcare.v1beta1.CloudHealthcareScopes;
import com.google.api.services.healthcare.v1beta1.model.FhirStore;
import com.google.api.services.healthcare.v1beta1.model.HttpBody;
//import com.google.api.services.healthcare.v1beta1.model.Resource;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.healthcare.v1beta1.model.SearchResourcesRequest;
//import com.google.api.services.healthcare.v1beta1.model.SearchResourcesResponse;


// import com.google.api.services.healthcare.v1.model.Parameters;
// import com.google.api.services.healthcare.v1beta1.model.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import com.google.api.client.http.HttpHeaders;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List; 
import java.io.InputStream;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonObject; 
import com.google.gson.JsonElement; 

import com.example.model.Test;
import com.example.model.TestLabTechPatientMap;
import com.example.service.TestService;
import com.example.service.TestLabTechPatientMapService;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tests")
public class TestController {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${fhir.store.id}")
    private String fhirStoreId;
    @Value("${fhir.dataset.id}")
    private String datasetId;
    @Value("${dataset.location.id}")
    private String locationId;

    @Value("${gcp.account.key.path}")
    private String serviceAccountKeyPath;

    @Autowired
    private TestService testService;

    @Autowired
    private TestLabTechPatientMapService testLabTechPatientMapService;

    @GetMapping("/getall")
    public List<Test> getAllTests() {
        // Retrieve tests based on the logged-in lab technician
        // You need to implement this logic based on your authentication mechanism
        Long labTechId = getLoggedInLabTechId();
        List<Test> tests  = testLabTechPatientMapService.getTestsByLabTechId(labTechId);
        // List<Test> tests = testLabTechPatientMaps.stream()
        //         .map(TestLabTechPatientMap::getTest)
        //         .collect(Collectors.toList());

        return tests;
    }

    @PostMapping("/create")
    public Test createTest(@RequestBody Test test) {
        // Save the test to the database
        return testService.saveTest(test);
    }

    @PutMapping("/{id}/complete")
    public Test completeTest(@PathVariable Long id) {
        // Update the corresponding test in the database
        return testService.completeTest(id);
    }

    // Helper method to get the logged-in lab technician's ID
    private Long getLoggedInLabTechId() {
        // Implement this method based on your authentication mechanism
        // You might use Spring Security's SecurityContextHolder or obtain it from the token
        // Here, I'm returning a dummy value as an example
        return 1L;
    }

    @PostMapping("/{id}/upload")
public ResponseEntity<String> uploadFhirFile(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("comments") String comments) {
    Test test = testService.findById(id);
    if (test == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Test not found");
    }

    if (test.isCompleted()) {
        throw new IllegalStateException("Test is already completed");
    }

    // Check if the file is not empty
    if (file.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
    }

    try {
        // Create the Google Cloud Healthcare API client
        CloudHealthcare client = createCloudHealthcareClient();

        // Convert the byte array to a string
        String fileData = new String(file.getBytes());

        // Create the FHIR resource to upload
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(fileData, JsonObject.class);
        String fhirFileContent = gson.toJson(jsonObject);

       
        String parent = String.format("projects/%s/locations/%s/datasets/%s/fhirStores/%s",
                projectId, locationId, datasetId, fhirStoreId);
        String restype = jsonObject.get("resourceType").getAsString();

        String contentType = "application/fhir+json;charset=utf-8";
        HttpContent httpContent = new ByteArrayContent(contentType, fhirFileContent.getBytes());
        
        // Make the API call to upload the FHIR resource
        HttpRequest request = client.getRequestFactory().buildPostRequest(
                new GenericUrl("https://healthcare.googleapis.com/v1beta1/" + parent + "/fhir/" + restype),
                httpContent
        );
        
        HttpResponse response = request.execute();

        // Handle the response if needed
        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            System.out.println("FHIR resource uploaded successfully.");
            test.setComments(comments);
            test.setCompleted(true);
            testService.saveTest(test);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("FHIR file uploaded successfully");
        } else {
            System.err.println("Error uploading FHIR resource. Status code: " + statusCode);
            System.err.println("Response content: " + response.parseAsString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during FHIR file upload");
        }
       
        // Update the test with the comments

        
    } catch (IOException | GeneralSecurityException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during FHIR file upload");
    }
}


    // @GetMapping("/{id}/files")
    // public List<Resource> getFhirFiles(@PathVariable String id) {
    //     try {
    //         // Create the Healthcare API client
    //         CloudHealthcare client = createCloudHealthcareClient();
    //         String resourceType = "DiagnosticReport";

    //         String parent = String.format("projects/%s/locations/%s/datasets/%s/fhirStores/%s",
    //         projectId, locationId, datasetId, fhirStoreId);

    //         SearchResourcesRequest request = new SearchResourcesRequest();
    //         // You can add more search parameters as needed.
    //         // For example, to search for all Patient resources:
    //         // request.setResourceType("Patient");

    //         // Execute the search request
    //         SearchResourcesResponse response = client.projects().locations().datasets()
    //         .fhirStores().fhir().search(parent, request).execute();

    //         // // Process the search response
    //         List<Resource> resources = response.getResources();
    //         // List<String> resourceIds = new ArrayList<>();
            
    //         // if (resources != null) {
    //         //     for (Resource resource : resources) {
    //         //         resourceIds.add(resource.getId());
    //         //         // Process other resource properties as needed.
    //         //     }
    //         // } else {
    //         //     System.out.println("No resources found.");
    //         // }

    //         return resources;
            
    //     } catch (IOException | GeneralSecurityException e) {
    //         e.printStackTrace();     
    //         return Collections.emptyList();
    //         //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }


    private CloudHealthcare createCloudHealthcareClient() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        HttpRequestInitializer requestInitializer = getRequestInitializer();

        return new CloudHealthcare.Builder(httpTransport, jsonFactory, requestInitializer)
                .setApplicationName("Your Application Name")
                .build();
    }

    private HttpRequestInitializer getRequestInitializer() throws IOException, GeneralSecurityException {

        Resource resource = new ClassPathResource(serviceAccountKeyPath);
        InputStream inputStream = resource.getInputStream();

        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream)
                .createScoped(Collections.singleton(CloudHealthcareScopes.CLOUD_PLATFORM));

        return new HttpCredentialsAdapter(credentials);
    }

}
