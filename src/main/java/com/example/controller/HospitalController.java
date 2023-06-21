import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//import com.example.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{hospitalId}")
    public Optional<Hospital> getHospitalById(@PathVariable String hospitalId) {
        return hospitalService.getHospitalById(hospitalId);
    }

    @PostMapping
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @PutMapping("/{hospitalId}")
    public Hospital updateHospital(@PathVariable String hospitalId, @RequestBody Hospital updatedHospital) {
        Optional<Hospital> hospital = hospitalService.getHospitalById(hospitalId);
        if (hospital.isPresent()) {
            Hospital existingHospital = hospital.get();
            existingHospital.setHospitalName(updatedHospital.getHospitalName());
            existingHospital.setUsername(updatedHospital.getUsername());
            existingHospital.setPassword(updatedHospital.getPassword());
            existingHospital.setEmailId(updatedHospital.getEmailId());
            existingHospital.setAddress1(updatedHospital.getAddress1());
            existingHospital.setAddress2(updatedHospital.getAddress2());
            existingHospital.setState(updatedHospital.getState());
            existingHospital.setCity(updatedHospital.getCity());
            existingHospital.setCountry(updatedHospital.getCountry());
            existingHospital.setZipCode(updatedHospital.getZipCode());
            existingHospital.setCreatedAt(updatedHospital.getCreatedAt());

            return hospitalService.updateHospital(existingHospital);
        } //else {
            //throw new NotFoundException("Hospital not found with ID: " + hospitalId);
       // }
       return null;
    }

    // @DeleteMapping("/{hospitalId}")
    // public void deleteHospital(@PathVariable String hospitalId) {
    //     hospitalService.deleteHospital(hospitalId);
    // }
}

