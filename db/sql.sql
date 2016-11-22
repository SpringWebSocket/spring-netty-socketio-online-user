/*
Navicat PGSQL Data Transfer

Source Server         : Localhost
Source Server Version : 90303
Source Host           : localhost:5432
Source Database       : dbSocket
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90303
File Encoding         : 65001

Date: 2016-11-22 22:41:06
*/


-- ----------------------------
-- Sequence structure for tbrole_id_seq
-- ----------------------------
DROP SEQUENCE "tbrole_id_seq";
CREATE SEQUENCE "tbrole_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."tbrole_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for tbuser_id_seq
-- ----------------------------
DROP SEQUENCE "tbuser_id_seq";
CREATE SEQUENCE "tbuser_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."tbuser_id_seq"', 4, true);

-- ----------------------------
-- Table structure for tbrole
-- ----------------------------
DROP TABLE IF EXISTS "tbrole";
CREATE TABLE "tbrole" (
"id" int4 DEFAULT nextval('tbrole_id_seq'::regclass) NOT NULL,
"name" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbrole
-- ----------------------------
BEGIN;
INSERT INTO "tbrole" VALUES ('1', 'Admin');
INSERT INTO "tbrole" VALUES ('2', 'Editor');
INSERT INTO "tbrole" VALUES ('3', 'User');
COMMIT;

-- ----------------------------
-- Table structure for tbuser
-- ----------------------------
DROP TABLE IF EXISTS "tbuser";
CREATE TABLE "tbuser" (
"id" int4 DEFAULT nextval('tbuser_id_seq'::regclass) NOT NULL,
"username" varchar COLLATE "default",
"email" varchar COLLATE "default",
"password" varchar COLLATE "default",
"status" bool DEFAULT true,
"online" bool DEFAULT false,
"client_id" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbuser
-- ----------------------------
BEGIN;
INSERT INTO "tbuser" VALUES ('1', 'phearun', 'phearun@gmail.com', '$2a$10$Cdh1EGlTh2DxWADySWVxWeYyfswjv7hT3fbDoPuMVJdS0smKJwvii', 't', 'f', '7a43c4ea-47a2-4f05-9d8d-0be994a66500');
INSERT INTO "tbuser" VALUES ('2', 'dara', 'dara@gmail.com', '$2a$10$3dMNYJSQDlrvnrMWiM8DB.hEDVkwYc0KZpt7bM/eiKy0u3fh2RqY6', 't', 't', '4a59527b-ecff-409f-8ffb-01273db04cee');
INSERT INTO "tbuser" VALUES ('3', 'daro', 'daro@gmail.com', '$2a$10$Cdh1EGlTh2DxWADySWVxWeYyfswjv7hT3fbDoPuMVJdS0smKJwvii', 't', 'f', '60ea20e8-0462-434f-988d-545044c2b87f');
INSERT INTO "tbuser" VALUES ('4', 'dareth', 'dareth@gmail.com', '$2a$10$Cdh1EGlTh2DxWADySWVxWeYyfswjv7hT3fbDoPuMVJdS0smKJwvii', 't', 'f', 'e470e90a-dbbf-4fea-88eb-68fa3c4a1d63');
COMMIT;

-- ----------------------------
-- Table structure for tbuser_detail
-- ----------------------------
DROP TABLE IF EXISTS "tbuser_detail";
CREATE TABLE "tbuser_detail" (
"user_id" int4 NOT NULL,
"role_id" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbuser_detail
-- ----------------------------
BEGIN;
INSERT INTO "tbuser_detail" VALUES ('1', '1');
INSERT INTO "tbuser_detail" VALUES ('2', '3');
INSERT INTO "tbuser_detail" VALUES ('3', '2');
INSERT INTO "tbuser_detail" VALUES ('4', '3');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "tbrole_id_seq" OWNED BY "tbrole"."id";
ALTER SEQUENCE "tbuser_id_seq" OWNED BY "tbuser"."id";

-- ----------------------------
-- Primary Key structure for table tbrole
-- ----------------------------
ALTER TABLE "tbrole" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tbuser
-- ----------------------------
ALTER TABLE "tbuser" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tbuser_detail
-- ----------------------------
ALTER TABLE "tbuser_detail" ADD PRIMARY KEY ("user_id", "role_id");

-- ----------------------------
-- Foreign Key structure for table "tbuser_detail"
-- ----------------------------
ALTER TABLE "tbuser_detail" ADD FOREIGN KEY ("user_id") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tbuser_detail" ADD FOREIGN KEY ("role_id") REFERENCES "tbrole" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
