db.getCollection("games").find({})

db.getCollection("games").count()

db.getCollection("games").find({
    ranking: 1
})

db.getCollection("games").find({}).sort({
    ranking: 1
})

db.getCollection("games").find({}).sort({
    ranking: 1
}).limit(1)

db.getCollection("comments").find({})


db.getCollection("comments").find({
    gid: 174430
}).sort({
    rating: -1
}).limit(5)