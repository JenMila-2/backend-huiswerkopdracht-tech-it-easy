package com.example.backendtechiteasy.controllers;
import com.example.backendtechiteasy.exceptions.RecordNotFoundException;
import com.example.backendtechiteasy.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/televisionDataBase")
public class TelevisionsController {

    private List<Television> televisionDataBase = new ArrayList<>();

    public TelevisionsController() {
        Television tv = new Television();
        tv.name = "Crystal";
        tv.type = "HD TV";
        tv.brand = "Philips";
        tv.price = 600;
        this.televisionDataBase.add(tv);
    }

    @GetMapping
    public ResponseEntity<List<Television>> getTelevisionDataBase() {
        //ResponseEntity.OK is code 200.
        return new ResponseEntity<>(televisionDataBase, HttpStatus.OK);
    }

  @GetMapping("/{id}")
    //Het is netter om bij een ID de @PathVariable te gebruiken. @PathVariable gebruik je om een pad op te zoeken.
  //Onderstaande code kan korter geschreven worden.
    public Television getTelevision(@PathVariable int id) throws RecordNotFoundException {
        if (id >= 0 && id < televisionDataBase.size()) {
            return this.televisionDataBase.get(id);
        } else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television tv) {
        televisionDataBase.add(tv);
        return new ResponseEntity<>(tv, HttpStatus.CREATED);
        //Gewenste feedback is een melding (tekst) dat het toevoegen gelukt is, inclusief het object dat is toegevoegd.
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable int id, @RequestBody Television updatedTelevision)  {
        if (id >= 0 && id < televisionDataBase.size() && updatedTelevision != null) {
            televisionDataBase.set(id, updatedTelevision);
            return ResponseEntity.ok("Television details updated successfully!");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) {
        televisionDataBase.remove(id);
        return ResponseEntity.noContent().build();
    }

}
