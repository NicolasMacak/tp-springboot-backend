;             
CREATE USER IF NOT EXISTS "SA" SALT '8d0442a996d4a833' HASH '35f7dafc09f0f2fdda089f5d1e31473284fdff33b40cdff103154ce2c5dd7dc6' ADMIN;         
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 2;   
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D01FE81F_EB70_4D1C_9901_A9A517857259" START WITH 2 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."CLIENTS"(
    "ID" BIGINT NOT NULL,
    "CONTACT_ID" BIGINT
);               
ALTER TABLE "PUBLIC"."CLIENTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" PRIMARY KEY("ID");      
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.CLIENTS; 
INSERT INTO "PUBLIC"."CLIENTS" VALUES
(1, 1);
CREATE MEMORY TABLE "PUBLIC"."CONTACTS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_D01FE81F_EB70_4D1C_9901_A9A517857259" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D01FE81F_EB70_4D1C_9901_A9A517857259",
    "FIRST_NAME" VARCHAR(255),
    "LAST_NAME" VARCHAR(255),
    "PHONE_NUMBER" VARCHAR(255)
);       
ALTER TABLE "PUBLIC"."CONTACTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY("ID");     
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.CONTACTS;
INSERT INTO "PUBLIC"."CONTACTS" VALUES
(1, 'Bojack', 'Horseman', '+421 452 654 280');        
ALTER TABLE "PUBLIC"."CLIENTS" ADD CONSTRAINT "PUBLIC"."FK70R0HTEQ9NQ6AGWM5K6H9R683" FOREIGN KEY("CONTACT_ID") REFERENCES "PUBLIC"."CONTACTS"("ID") NOCHECK;  
