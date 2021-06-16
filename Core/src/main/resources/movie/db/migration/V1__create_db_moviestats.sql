CREATE TABLE IF NOT EXISTS "Movie"
(
    "GUID"        character varying(36)  not null,
    "Name"        character varying(500) not null,
    "ReleaseDate" date,
    "WatchDate"   date,
    "Genre"       character varying(50),
    "Runtime"     integer,
    "ImageLink"   character varying(1000),
    "Series"      boolean,
    "Episodes"    integer,
    CONSTRAINT "PK_Movie" PRIMARY KEY ("GUID")
);

CREATE TABLE IF NOT EXISTS "User"
(
    "GUID"     character varying(36)  not null,
    "UserName" character varying(500) not null,
    "Password" character varying(500) not null,
    "Email"    character varying(500),
    "Name"     character varying(500),
    "LastName" character varying(500),
    CONSTRAINT "PK_Movie" PRIMARY KEY ("GUID")
);
