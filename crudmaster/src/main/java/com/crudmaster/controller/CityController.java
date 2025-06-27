package com.crudmaster.controller;

import com.crudmaster.dto.city.*;
import com.crudmaster.entity.CityEntity;
import com.crudmaster.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getCity(@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){

           List<CityDto> cities= cityService.getCity(pageNumber-1,pageSize);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @PostMapping("/cities")
    public ResponseEntity<CityDto>  postCity(@RequestBody CityCreateDto cityCreateDto){
         CityDto cityDto=cityService.addCity(cityCreateDto);
        return new ResponseEntity<>(cityDto,HttpStatus.OK);
    }
    @PutMapping("/cities/{cityId}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long cityId,@RequestBody CityUpdateDto cityUpdateDto){
        CityDto cityDto= cityService.updateCity(cityId,cityUpdateDto);
        return new ResponseEntity<>(cityDto,HttpStatus.OK);
    }
    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity<String> deleteCity(@PathVariable Long cityId){
        try {
            String deletemsg = cityService.deleteCity(cityId);
            return new ResponseEntity<>(deletemsg,HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/cities/{cityId}")
    public ResponseEntity<String> patchCity(@PathVariable Long cityId,@RequestBody CityDto cityDto){
        Boolean b=cityService.patchCity(cityId,cityDto);
        if(b){
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.badRequest().body("Failed to be updated...");
    }

    @PostMapping("/cities/search")
    public ResponseEntity<CityFilterReturnDto> searchCity(@RequestBody CityFilterDto cityFilterDto){
        return new ResponseEntity(cityService.searchCity(cityFilterDto),HttpStatus.FOUND);
    }



}
