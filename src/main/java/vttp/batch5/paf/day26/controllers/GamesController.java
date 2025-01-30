package vttp.batch5.paf.day26.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import vttp.batch5.paf.day26.services.GamesService;

@RestController
@RequestMapping("/api/search")
public class GamesController {
    @Autowired
    private GamesService gamesSvc;

    @GetMapping
    public ResponseEntity<String> getHighestRatedGame(@RequestParam String q) {
        String response;

        // 404 - no games in DB
        if(gamesSvc.getTotalGamesCount() < 1) {
            response = Json.createObjectBuilder()
                        .add("message", "ERROR: No games found in DB")
                        .build()
                        .toString();
                        
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Get highest ranked game
        if(q.equals("no1")) {
            // 200 OK
            response = gamesSvc.getHighestRatedGame();
            return ResponseEntity.ok(response);
        }

        // 200 OK
        //response = gamesSvc.getGameByName(q);
        return ResponseEntity.ok().body("{}");
    }
}
