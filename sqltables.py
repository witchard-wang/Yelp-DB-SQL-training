import psycopg2
import os

# Connect to 315 database
# Use an environment variable too access db password
conn = psycopg2.connect(
    "host=csce-315-db.engr.tamu.edu dbname=team910_d6_db user=mxb0417 password=727005354" + os.environ['DB_315_PASS'])
cur = conn.cursor()

# Create a test table in the database
cur.execute("""
    CREATE TABLE "User"
    (
    "UserID"      char(22) NOT NULL,
    "UserName"    varchar(50) NOT NULL,
    "ReviewCount" int NOT NULL,
    "OnYelpSince" date NOT NULL,
    CONSTRAINT "PK_user" PRIMARY KEY ( "UserID" )
    );








    -- ************************************** "Business"

    CREATE TABLE "Business"
    (
    "BusinessID"   char(22) NOT NULL,
    "BusinessName" varchar(50) NOT NULL,
    "Stars"        int NOT NULL,
    "ReviewCount"  int NOT NULL,
    CONSTRAINT "PK_table1" PRIMARY KEY ( "BusinessID" )
    );








    -- ************************************** "Tips"

    CREATE TABLE "Tips"
    (
    "TipID"       int NOT NULL,
    "Text"        varchar(5000) NOT NULL,
    "Date"        date NOT NULL,
    "Compliments" int NOT NULL,
    "BusinessID"  char(22) NOT NULL,
    "UserID"      char(22) NOT NULL,
    CONSTRAINT "PK_tips" PRIMARY KEY ( "TipID" ),
    CONSTRAINT "FK_54" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" ),
    CONSTRAINT "FK_57" FOREIGN KEY ( "UserID" ) REFERENCES "User" ( "UserID" )
    );

    CREATE INDEX "fkIdx_54" ON "Tips"
    (
    "BusinessID"
    );

    CREATE INDEX "fkIdx_57" ON "Tips"
    (
    "UserID"
    );








    -- ************************************** "Service"

    CREATE TABLE "Service"
    (
    "CategoryID" int NOT NULL,
    "BusinessID" char(22) NOT NULL,
    "Types"      varchar(500) NOT NULL,
    CONSTRAINT "PK_service" PRIMARY KEY ( "CategoryID" ),
    CONSTRAINT "FK_85" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" )
    );

    CREATE INDEX "fkIdx_85" ON "Service"
    (
    "BusinessID"
    );








    -- ************************************** "Review"

    CREATE TABLE "Review"
    (
    "ReviewID"   char(22) NOT NULL,
    "Stars"      int NOT NULL,
    "DatePosted" date NOT NULL,
    "Text"       varchar(5000) NOT NULL,
    "BusinessID" char(22) NOT NULL,
    "UserID"     char(22) NOT NULL,
    CONSTRAINT "PK_review" PRIMARY KEY ( "ReviewID" ),
    CONSTRAINT "FK_33" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" ),
    CONSTRAINT "FK_36" FOREIGN KEY ( "UserID" ) REFERENCES "User" ( "UserID" )
    );

    CREATE INDEX "fkIdx_33" ON "Review"
    (
    "BusinessID"
    );

    CREATE INDEX "fkIdx_36" ON "Review"
    (
    "UserID"
    );








    -- ************************************** "Retail"

    CREATE TABLE "Retail"
    (
    "CategoryID" int NOT NULL,
    "BusinessID" char(22) NOT NULL,
    "Types"      varchar(500) NOT NULL,
    CONSTRAINT "PK_retail" PRIMARY KEY ( "CategoryID" ),
    CONSTRAINT "FK_82" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" )
    );

    CREATE INDEX "fkIdx_82" ON "Retail"
    (
    "BusinessID"
    );








    -- ************************************** "Restaurant"

    CREATE TABLE "Restaurant"
    (
    "CategoryID" int NOT NULL,
    "BusinessID" char(22) NOT NULL,
    "Types"      varchar(500) NOT NULL,
    CONSTRAINT "PK_service" PRIMARY KEY ( "CategoryID" ),
    CONSTRAINT "FK_88" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" )
    );

    CREATE INDEX "fkIdx_88" ON "Restaurant"
    (
    "BusinessID"
    );








    -- ************************************** "Location"

    CREATE TABLE "Location"
    (
    "LocationID" int NOT NULL,
    "Street"     varchar(50) NOT NULL,
    "City"       varchar(50) NOT NULL,
    "State"      varchar(50) NOT NULL,
    "ZipCode"    int NOT NULL,
    "BusinessID" char(22) NOT NULL,
    CONSTRAINT "PK_location" PRIMARY KEY ( "LocationID" ),
    CONSTRAINT "FK_18" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" )
    );

    CREATE INDEX "fkIdx_18" ON "Location"
    (
    "BusinessID"
    );








    -- ************************************** "Hospitality"

    CREATE TABLE "Hospitality"
    (
    "CategoryID" int NOT NULL,
    "BusinessID" char(22) NOT NULL,
    "Types"      varchar(500) NOT NULL,
    CONSTRAINT "PK_hospitality" PRIMARY KEY ( "CategoryID" ),
    CONSTRAINT "FK_76" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" )
    );

    CREATE INDEX "fkIdx_76" ON "Hospitality"
    (
    "BusinessID"
    );








    -- ************************************** "Entertainment"

    CREATE TABLE "Entertainment"
    (
    "CategoryID" int NOT NULL,
    "BusinessID" char(22) NOT NULL,
    "Types"      varchar(500) NOT NULL,
    CONSTRAINT "PK_entertainment" PRIMARY KEY ( "CategoryID" ),
    CONSTRAINT "FK_79" FOREIGN KEY ( "BusinessID" ) REFERENCES "Business" ( "BusinessID" )
    );

    CREATE INDEX "fkIdx_79" ON "Entertainment"
    (
    "BusinessID"
    );








    -- ************************************** "ReviewVotes"

    CREATE TABLE "ReviewVotes"
    (
    "ReviewVotesID" int NOT NULL,
    "Useful"        int NOT NULL,
    "Funny"         int NOT NULL,
    "Cool"          int NOT NULL,
    "ReviewID"      char(22) NOT NULL,
    CONSTRAINT "PK_reviewvotes" PRIMARY KEY ( "ReviewVotesID" ),
    CONSTRAINT "FK_45" FOREIGN KEY ( "ReviewID" ) REFERENCES "Review" ( "ReviewID" )
    );

    CREATE INDEX "fkIdx_45" ON "ReviewVotes"
    (
    "ReviewID"
    );
""")

# Commit everything to the database
conn.commit()
