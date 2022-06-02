INSERT INTO usr_user ( email, password, enabled,created_by,create_date,update_date,deleted,last_modified_by) VALUES
    ('ramazan.karagoz@gmail.com','$2a$10$mUf92VpprgXHg5Ddyi/EBu5ddC7iAh02w.K0brkIDhVRb05IjRTkq',true ,'admin',null ,null ,true,null);
INSERT INTO role (role_name,created_by,create_date,update_date,deleted,last_modified_by) VALUES ('ROLE_ADMIN','admin',null ,null ,true,null),('ROLE_USER','admin',null ,null ,true,null);
INSERT INTO privilege (privilege_name,created_by,create_date,update_date,deleted,last_modified_by) VALUES ('READ','admin',null ,null ,true,null),('WRITE','admin',null ,null ,true,null),('DELETE','admin',null ,null ,true,null);
INSERT INTO privileges_roles(role_id,privilege_id) VALUES (1,1),(1,2),(1,3),(2,1);
INSERT INTO users_roles(user_id,role_id) VALUES (1,1);