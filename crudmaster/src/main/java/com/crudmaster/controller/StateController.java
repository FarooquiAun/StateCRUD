package com.crudmaster.controller;


import com.crudmaster.dto.state.StateDto;
import com.crudmaster.dto.state.StateFilterDto;
import com.crudmaster.dto.state.StateFilterReturnDto;
import com.crudmaster.entity.StateEntity;
import com.crudmaster.service.StateServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StateController {

    private StateServiceImpl stateService;

    public StateController(StateServiceImpl stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/states")
    public ResponseEntity<StateDto> addState(@RequestBody StateDto stateDto){
        StateDto states =stateService.saveAll(stateDto);
        return new ResponseEntity<>(states, HttpStatus.CREATED);
    }
    @GetMapping("/states")
    public ResponseEntity<List<StateDto>> getAllStates(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue ="5",required = false) Integer pageSize
    ){
        List<StateDto> states=stateService.getAll(pageNumber-1,pageSize);
        return new ResponseEntity<>(states,HttpStatus.OK);
    }

    @PutMapping("/states/{stateId}")
    public ResponseEntity<String> updateState(@PathVariable long stateId,@RequestBody StateDto stateDto){
        Boolean b=stateService.updateState(stateId,stateDto);
        if(b){
            return ResponseEntity.ok("updated");
        }
        return ResponseEntity.badRequest().body("Failed to be updated...");
    }
    @PatchMapping("/states/{stateId}")
    public ResponseEntity<String> patchState(@PathVariable long stateId,@RequestBody StateDto stateDto){
        Boolean b=stateService.patchState(stateId,stateDto);
        if(b){
            return ResponseEntity.ok("updated");
        }
        return ResponseEntity.badRequest().body("Failed to be updated...");
    }

    @DeleteMapping("/states/{stateId}")
    public ResponseEntity<String>  deleteState(@PathVariable long stateId){
           try{
                        String str= stateService.deleteState(stateId);
                        return new ResponseEntity<>(str,HttpStatus.OK);
               } catch (Exception e){
              throw new RuntimeException(e);
           }
    }

    @PostMapping("/states/search")
    public ResponseEntity<Page<StateFilterReturnDto>> searchStates(@RequestBody StateFilterDto stateFilterDto){
        return new ResponseEntity<>(stateService.searchState(stateFilterDto),HttpStatus.FOUND);
    }


    @PostMapping(value = "/states/upload" , consumes = "multipart/form-data")
    public ResponseEntity<String> importStateData(@RequestParam MultipartFile file){
        return ResponseEntity.ok(stateService.importStates(file));
    }

}
