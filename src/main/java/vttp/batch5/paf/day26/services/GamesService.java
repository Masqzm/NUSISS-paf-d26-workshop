package vttp.batch5.paf.day26.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.paf.day26.repositories.CommentsRepo;
import vttp.batch5.paf.day26.repositories.GamesRepo;

import static vttp.batch5.paf.Constants.*;

@Service
public class GamesService {
    @Autowired
    GamesRepo gamesRepo;
    @Autowired
    CommentsRepo commentsRepo;

    public Long getTotalGamesCount() {
        return gamesRepo.getTotalGamesCount();
    }

    public String getHighestRatedGame() {
        Document highestRatedGame = gamesRepo.findHighestRatedGame();

        int gid = highestRatedGame.getInteger("gid");
        
        List<Document> comments = commentsRepo.findCommentsByGID(gid);
        

        // Create JSON of game and comments results
        JsonArrayBuilder jArrB_comments = Json.createArrayBuilder();
        
        for(Document d : comments) {
            JsonObject j = Json.createObjectBuilder()
                    .add(F_COMMENTS_USER, d.getString(F_COMMENTS_USER))
                    .add(F_COMMENTS_RATING, d.getInteger(F_COMMENTS_RATING))
                    .add("text", d.getString(F_COMMENTS_TEXT))
                    .build();
                    
            jArrB_comments.add(j);
        }

        JsonObject j = Json.createObjectBuilder()
                        .add(F_GID, gid)
                        .add(F_NAME, highestRatedGame.getString(F_NAME))
                        .add(F_RANKING, highestRatedGame.getInteger(F_RANKING))
                        .add(F_URL, highestRatedGame.getString(F_URL))
                        .add(F_IMAGE, highestRatedGame.getString(F_IMAGE))
                        .add(F_COMMENTS, jArrB_comments.build())
                        .build();

        System.out.println(j.toString());

        return j.toString();
    }

    // public String getGameByName(String q) {
    //     //int gid = gamesRepo.findGameByName(q).getInteger("gid");
    // }
}
