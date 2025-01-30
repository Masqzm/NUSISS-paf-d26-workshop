package vttp.batch5.paf.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp.batch5.paf.Constants.*;

@Repository
public class GamesRepo {
    @Autowired
    private MongoTemplate template;

    public long getTotalGamesCount() {
        return template.count(new Query(), C_GAMES);
    }

    public List<Document> findGameByName(String name) {
        return findGameByName(name, 25, 0);
    }
    public List<Document> findGameByName(String name, int limit, int offset) {
        // Define search criteria
        Criteria criteria_by_name = Criteria.where(F_NAME)
                                    .regex(name, "i");

        // Create query
        Query query = Query.query(criteria_by_name)
                    .limit(limit)
                    .skip(offset);

        // Perform search
        return template.find(query, Document.class, C_GAMES);
    }

    /*
        db.getCollection("games").find({
            ranking: 1
        })
     */
    public Document findHighestRatedGame() {
        // Define search criteria
        Criteria criteria_by_rank = Criteria.where(F_RANKING).is(1);

        // Create query
        Query query = Query.query(criteria_by_rank);

        query.fields()
        .exclude(F_ID)
        .include(F_GID)
        .include(F_NAME)
        .include(F_RANKING)
        .include(F_URL)
        .include(F_IMAGE);

        // Perform search
        return template.findOne(query, Document.class, C_GAMES);
    }
}
