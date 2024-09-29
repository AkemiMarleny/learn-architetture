package co.idesoft.architetture.cqrs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.architetture.cqrs.commands.AggiornareFornitoreCommand;
import co.idesoft.architetture.cqrs.commands.CreareFornitoreCommand;
import co.idesoft.architetture.cqrs.dtos.AggiotnareFornitoreDto;
import co.idesoft.architetture.cqrs.dtos.CreareFornitoreDto;
import co.idesoft.architetture.cqrs.dtos.FornitoreCreatoDto;
import co.idesoft.architetture.cqrs.services.FornitoreService;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/fornitori")
@RequiredArgsConstructor
@Slf4j
public class FornitoreWriterController {

    private final FornitoreService fornitoreService;

    @PostMapping
    public ResponseEntity<FornitoreCreatoDto> creareFornitore(@RequestBody CreareFornitoreDto request) {

        Long fornitoreId = fornitoreService.creareFornitore(
                new CreareFornitoreCommand(request.nome(), request.descrizione()));

        return ResponseEntity.ok(new FornitoreCreatoDto(fornitoreId));
    }

    @PutMapping("{fornitoreId}")
    public ResponseEntity<Void> updateFornitore(@PathVariable Long fornitoreId, 
    @RequestBody AggiotnareFornitoreDto request){
        try {
            fornitoreService.update(fornitoreId, 
                new AggiornareFornitoreCommand(request.nome(), request.descrizione()));
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

    @DeleteMapping("{fornitoreId}")
    public ResponseEntity<Void> cancellaFornitore(@PathVariable Long fornitoreId){
        fornitoreService.cancella(fornitoreId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
