package vttp.batch5.paf.day26.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp.batch5.paf.day26.services.GamesService;

@RestController
@RequestMapping("/api/search")
public class GamesController {
    @Autowired
    private GamesService gamesSvc;

    @GetMapping
    public ResponseEntity<String> getHighestRatedGame(@RequestParam String q) {
        if(q.equals("no1")) {
            String response = gamesSvc.getHighestRatedGame();

            // 200 OK
            if(response != null && !response.isBlank())
                return ResponseEntity.ok(response);
        }

        // // 404
        return ResponseEntity.notFound().build();
    }
}
