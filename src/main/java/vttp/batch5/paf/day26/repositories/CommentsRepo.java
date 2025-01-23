package vttp.batch5.paf.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp.batch5.paf.Constants.*;

@Repository
public class CommentsRepo {
    @Autowired
    private MongoTemplate template;

    /*
        db.getCollection("comments").find({
            gid: <gid>
        }).sort({
            rating: -1
        }).limit(5)
     */
    public List<Document> findCommentsByGID(int gid) {
        return findCommentsByGID(gid, 5, 0);
    }
    public List<Document> findCommentsByGID(int gid, int limit, int offset) {
        // Define search criteria
        Criteria criteria_by_GID = Criteria.where(F_GID).is(gid);

        // Create query
        Query query = Query.query(criteria_by_GID)
                    .with(Sort.by(Sort.Direction.DESC, F_COMMENTS_RATING))
                    .limit(limit)
                    .skip(offset);

        query.fields()
        .exclude(F_ID)
        .include(F_COMMENTS_USER)
        .include(F_COMMENTS_RATING)
        .include(F_COMMENTS_TEXT);

        // Perform search
        return template.find(query, Document.class, C_COMMENTS);
    }
}
