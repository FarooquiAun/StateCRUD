package com.crudmaster.controller;

import com.crudmaster.dto.pincode.*;
import com.crudmaster.entity.PincodeEntity;
import com.crudmaster.service.PincodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PincodeController {

    private PincodeService pincodeService;

    public PincodeController(PincodeService pincodeService) {
        this.pincodeService = pincodeService;
    }

    @PostMapping("/pincodes")
    public ResponseEntity<PincodeDto> addPincode(@RequestBody PincodeCreateDto pincodeCreateDto){
        PincodeDto pincodeDto =pincodeService.addPincode(pincodeCreateDto);
        return new ResponseEntity<>(pincodeDto ,HttpStatus.CREATED);
    }
    @GetMapping("/pincodes")
    public ResponseEntity<List<PincodeDto>> getPincode(@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
        List<PincodeDto> pincodeDto=pincodeService.getPincode(pageNumber-1,pageSize);
        return new ResponseEntity<>(pincodeDto,HttpStatus.OK);

    }
    @PutMapping("/pincodes/{pincodeId}")
    public ResponseEntity<PincodeDto> updatePincode(@PathVariable Long pinocdeId,
                                                       @RequestBody PincodeUpdateDto pincodeUpdateDto ){
        PincodeDto pincodeDto= pincodeService.updatePincode(pinocdeId,pincodeUpdateDto);
        return new ResponseEntity<>(pincodeDto,HttpStatus.OK);
    }
    @DeleteMapping("/pincodes/{pincodeId}")
    public ResponseEntity<String> deletePincode(@PathVariable Long pincodeId) {
        try {
            String msg = pincodeService.deletePincode(pincodeId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/pincodes/search")
    public ResponseEntity<PincodeFilterReturnDto> searchPincode(@RequestBody PincodeFilterDto pincodeFilterDto){

        return new ResponseEntity(pincodeService.searchPincode(pincodeFilterDto),HttpStatus.FOUND);
    }

    @PostMapping(value = "/pincodes/upload",consumes = "multipart/form-data")
    public ResponseEntity<String>  importPincode(@RequestParam MultipartFile file){
        return ResponseEntity.ok(pincodeService.importPincodes(file));
    }

}
