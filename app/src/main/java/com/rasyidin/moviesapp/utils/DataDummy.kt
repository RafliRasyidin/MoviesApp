package com.rasyidin.moviesapp.utils

import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.model.Movies
import com.rasyidin.moviesapp.model.Tv

object DataDummy {

    fun generateDummyMovies(): List<Movies> {

        val movies = ArrayList<Movies>()

        movies.add(
                Movies(
                        1,
                        "Alita Battle Angel",
                        "2019",
                        "71",
                        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                        R.drawable.poster_alita,
                        "Action, Science Fiction, Adventure"
                )
        )
        movies.add(
                Movies(
                        2,
                        "A Star Is Born",
                        "2018",
                        "75",
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                        R.drawable.poster_a_star_is_born,
                        "Drama, Romance, Music"
                )
        )
        movies.add(
                Movies(
                        3,
                        "Aquaman",
                        "2018",
                        "69",
                        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                        R.drawable.poster_aquaman,
                        "Action, Adventure, Fantasy"
                )
        )
        movies.add(
                Movies(
                        4,
                        "Bohemian Rhapsody",
                        "2018",
                        "80",
                        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                        R.drawable.poster_bohemian,
                        "Drama, Music"
                )
        )
        movies.add(
                Movies(
                        5,
                        "Cold Pursuit",
                        "2018",
                        "56",
                        "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                        R.drawable.poster_cold_persuit,
                        "Action, Crime, Thriller"
                )
        )
        movies.add(
                Movies(
                        6,
                        "Creed II",
                        "2018",
                        "69",
                        "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                        R.drawable.poster_creed,
                        "Drama"
                )
        )
        movies.add(
                Movies(
                        7,
                        "Fantastic Beasts: The Crimes of Grindelwald",
                        "2018",
                        "69",
                        "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                        R.drawable.poster_crimes,
                        "Adventure, Fantasy, Drama"
                )
        )
        movies.add(
                Movies(
                        8,
                        "Glass",
                        "2019",
                        "66",
                        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                        R.drawable.poster_glass,
                        "Thriller, Drama, Science Fiction"
                )
        )
        movies.add(
                Movies(
                        9,
                        "How to Train Your Dragon: The Hidden World",
                        "2019",
                        "78",
                        "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                        R.drawable.poster_how_to_train,
                        "Animation, Family, Adventure"
                )
        )
        movies.add(
                Movies(
                        10,
                        "Avengers: Infinity War",
                        "2018",
                        "83",
                        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                        R.drawable.poster_infinity_war,
                        "Adventure, Action, Science Fiction"
                )
        )

        return movies
    }

    fun generateDummyTv(): List<Tv> {

        val tv = ArrayList<Tv>()

        tv.add(
                Tv(
                        1,
                        "Marvel's Iron Fist",
                        "2017",
                        "64",
                        "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                        "Action & Adventure, Drama, Sci-Fi & Fantasy",
                        R.drawable.poster_iron_fist
                )
        )
        tv.add(
                Tv(
                        2,
                        "Naruto Shippūden",
                        "2007",
                        "87",
                        "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                        "Animation, Comedy, Drama",
                        R.drawable.poster_naruto_shipudden
                )
        )
        tv.add(
                Tv(
                        3,
                        "NCIS",
                        "2003",
                        "71",
                        "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                        "Action & Adventure, Crime, Drama",
                        R.drawable.poster_ncis
                )
        )
        tv.add(
                Tv(
                        4,
                        "Riverdale",
                        "2017",
                        "86",
                        "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                        "Drama, Mystery",
                        R.drawable.poster_riverdale
                )
        )
        tv.add(
                Tv(
                        5,
                        "Shameless",
                        "2011",
                        "79",
                        "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                        "Drama, Comedy",
                        R.drawable.poster_shameless
                )
        )
        tv.add(
                Tv(
                        6,
                        "Supergirl",
                        "2015",
                        "71",
                        "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                        "Action, Adventure, Drama, Science Fiction",
                        R.drawable.poster_supergirl
                )
        )
        tv.add(
                Tv(
                        7,
                        "Supernatural",
                        "2005",
                        "81",
                        "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                        "Drama, Mystery, Sci-Fi & Fantasy",
                        R.drawable.poster_supernatural
                )
        )
        tv.add(
                Tv(
                        8,
                        "The Simpsons",
                        "1989",
                        "77",
                        "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                        "Animation, Comedy, Family, Drama",
                        R.drawable.poster_the_simpson
                )
        )
        tv.add(
                Tv(
                        9,
                        "The Umbrella Academy",
                        "2019",
                        "87",
                        "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                        "Action & Adventure, Sci-Fi & Fantasy, Comedy, Drama",
                        R.drawable.poster_the_umbrella

                )
        )
        tv.add(
                Tv(
                        10,
                        "The Walking Dead",
                        "2010",
                        "79",
                        "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                        "Action & Adventure, Drama, Sci-Fi & Fantasy",
                        R.drawable.poster_the_walking_dead
                )
        )
        return tv
    }

    fun getDetailMovie(movieId: Int, listMovie: ArrayList<Movies>): Movies = listMovie[movieId-1]

    fun getDetailTv(tvId: Int, listTv: ArrayList<Tv>): Tv = listTv[tvId-1]
}