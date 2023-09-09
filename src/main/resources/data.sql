/*location*/
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1392685764', 'Tokyo', 'Tokyo', '35.6897', '139.6922', 'Japan', 'JP', 'JPN', 'Tōkyō', '37732000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1360771077', 'Jakarta', 'Jakarta', '-6.1750', '106.8275', 'Indonesia', 'ID', 'IDN', 'Jakarta', '33756000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1356872604', 'Delhi', 'Delhi', '28.6100', '77.2300', 'India', 'IN', 'IND', 'Delhi', '32226000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1156237133', 'Guangzhou', 'Guangzhou', '23.1300', '113.2600', 'China', 'CN', 'CHN', 'Guangdong', '26940000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1356226629', 'Mumbai', 'Mumbai', '19.0761', '72.8775', 'India', 'IN', 'IND', 'Mahārāshtra', '24973000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1608618140', 'Manila', 'Manila', '14.5958', '120.9772', 'Philippines', 'PH', 'PHL', 'Manila', '24922000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1156073548', 'Shanghai', 'Shanghai', '31.1667', '121.4667', 'China', 'CN', 'CHN', 'Shanghai', '24073000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1076532519', 'São Paulo', 'Sao Paulo', '-23.5500', '-46.6333', 'Brazil', 'BR', 'BRA', 'São Paulo', '23086000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1410836482', 'Seoul', 'Seoul', '37.5600', '126.9900', 'South Korea', 'KR', 'KOR', 'Seoul', '23016000');
INSERT INTO Location (id, city, city_ascii, lat, lng, country, iso2, iso3, capital, population) VALUES ('1484247881', 'Mexico City', 'Mexico City', '19.4333', '-99.1333', 'Mexico', 'MX', 'MEX', 'Ciudad de México', '21804000');

/*user*/
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('Alejandro García López', 'correo1@gmail.com', 'Director de Marmol SL', 'Cara 1.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'active', false, STR_TO_DATE ('12/1/2021', '%d/%m/%Y'), 1076532519);
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('Ana Martínez Sánchez', 'correo2@gmail.com', 'Director de Plata SL', 'Cara 2.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'active', false, STR_TO_DATE ('21/3/2021', '%d/%m/%Y'), 1076532519);
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('Rafael Núñez Fernández', 'correo3@gmail.com', 'Empleado de Prograo', 'Cara 3.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'actived', false, STR_TO_DATE ('2/6/2020', '%d/%m/%Y'), 1076532519);
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('Laura Fernández Pérez', 'correo4@gmail.com', 'Software Developer', 'Cara 4.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'active', false, STR_TO_DATE ('17/5/2021', '%d/%m/%Y'), 1076532519);
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('Carmen González Rodríguez', 'correo5@gmail.com', 'Graphic Designer', 'Cara 5.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'active', false, STR_TO_DATE ('1/11/2023', '%d/%m/%Y'), 1076532519);
INSERT INTO user (email, profile_photo, password, status, verified, registration_date) VALUES ('correo6@gmail.com', 'default.png', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'inactive', false, STR_TO_DATE ('17/06/2023', '%d/%m/%Y'));
INSERT INTO user (email, profile_photo, password, status, verified, registration_date) VALUES ('correo7@gmail.com', 'default.png', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'suspended', false, STR_TO_DATE ('17/06/2023', '%d/%m/%Y'));
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('Juan Ramírez López', 'correo8@gmail.com', 'Gestora de base de datos', 'Cara 8.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'active', false, STR_TO_DATE ('1/11/2023', '%d/%m/%Y'), 1076532519);
INSERT INTO user (name, email, title, profile_photo, password, status, verified, registration_date, location_id) VALUES ('María Torres Martín', 'correo9@gmail.com', 'Tester', 'Cara 7.jpg', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'active', true, STR_TO_DATE ('1/11/2023', '%d/%m/%Y'), 1076532519);



/*administrator*/
INSERT INTO administrator (user_id) VALUES (3);

/*seeker*/
INSERT INTO seeker (user_id) VALUES (1);
INSERT INTO seeker (user_id) VALUES (2);
INSERT INTO seeker (user_id) VALUES (7);

/*freelancer*/
INSERT INTO freelancer (rate, description, twitter, facebook, email, linkedin, user_id) VALUES (50, 'Me dedico a crear páginas web a empresas y particulares, manejándome en distintos lenguajes y frameworks para adaptarme al cliente. Además, soy capaz de manejarme en programas de diseño gráfico', 'johndoe', 'johndoe', 'john.doe@email.com', 'johndoe', 4);
INSERT INTO freelancer (rate, description, twitter, facebook, email, linkedin, user_id) VALUES (40, 'Creative graphic designer', 'janesmith', 'janesmith', 'jane.smith@email.com', 'janesmith', 5);
INSERT INTO freelancer (rate, user_id) VALUES (0, 6);
INSERT INTO freelancer (rate, user_id) VALUES (0, 8);
INSERT INTO freelancer (rate, user_id) VALUES (0, 9);


/*skill*/
INSERT INTO skill (name) VALUES ('Java');
INSERT INTO skill (name) VALUES ('Spring');
INSERT INTO skill (name) VALUES ('Spring Data');
INSERT INTO skill (name) VALUES ('HTML');
INSERT INTO skill (name) VALUES ('CSS');
INSERT INTO skill (name) VALUES ('Javascript');
INSERT INTO skill (name) VALUES ('Typescript');
INSERT INTO skill (name) VALUES ('Angular');
INSERT INTO skill (name) VALUES ('React');
INSERT INTO skill (name) VALUES ('Vue.js');
INSERT INTO skill (name) VALUES ('Node.js');
INSERT INTO skill (name) VALUES ('Express.js');
INSERT INTO skill (name) VALUES ('MongoDB');
INSERT INTO skill (name) VALUES ('MySQL');
INSERT INTO skill (name) VALUES ('PostgreSQL');
INSERT INTO skill (name) VALUES ('Python');
INSERT INTO skill (name) VALUES ('Django');
INSERT INTO skill (name) VALUES ('Flask');
INSERT INTO skill (name) VALUES ('Ruby');
INSERT INTO skill (name) VALUES ('Rails');
INSERT INTO skill (name) VALUES ('PHP');
INSERT INTO skill (name) VALUES ('Laravel');
INSERT INTO skill (name) VALUES ('Swift');
INSERT INTO skill (name) VALUES ('Kotlin');
INSERT INTO skill (name) VALUES ('C#');
INSERT INTO skill (name) VALUES ('.NET');
INSERT INTO skill (name) VALUES ('C++');
INSERT INTO skill (name) VALUES ('C');
INSERT INTO skill (name) VALUES ('Go');
INSERT INTO skill (name) VALUES ('Rust');
INSERT INTO skill (name) VALUES ('Ruby on Rails');
INSERT INTO skill (name) VALUES ('WordPress');
INSERT INTO skill (name) VALUES ('Joomla');
INSERT INTO skill (name) VALUES ('Drupal');
INSERT INTO skill (name) VALUES ('Unity');
INSERT INTO skill (name) VALUES ('Unreal Engine');
INSERT INTO skill (name) VALUES ('Git');
INSERT INTO skill (name) VALUES ('AWS');
INSERT INTO skill (name) VALUES ('Azure');
INSERT INTO skill (name) VALUES ('Google Cloud Platform');
INSERT INTO skill (name) VALUES ('Docker');
INSERT INTO skill (name) VALUES ('Kubernetes');
INSERT INTO skill (name) VALUES ('TensorFlow');
INSERT INTO skill (name) VALUES ('PyTorch');
INSERT INTO skill (name) VALUES ('Machine Learning');
INSERT INTO skill (name) VALUES ('Artificial Intelligence');
INSERT INTO skill (name) VALUES ('Data Science');
INSERT INTO skill (name) VALUES ('Big Data');
INSERT INTO skill (name) VALUES ('DevOps');
INSERT INTO skill (name) VALUES ('Scrum');
INSERT INTO skill (name) VALUES ('Agile Methodology');

/*freelancer_skill*/
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 1);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 1, 2);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 1, 3);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 2, 3);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 4);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 5);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 4, 11);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 4, 13);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 4, 15);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 5, 8);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (1, 5, 9);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 6);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 7);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 8);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 9);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 10);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 11);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 12);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 13);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 14);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 1, 15);

INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 2, 6);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 2, 7);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 2, 8);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 2, 9);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 2, 10);

INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 3, 6);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 3, 7);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 3, 8);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 3, 9);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 3, 10);

INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 4, 6);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 4, 7);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 4, 8);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 4, 9);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 4, 10);

INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 5, 6);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 5, 7);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 5, 8);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 5, 9);
INSERT INTO freelancer_skill (outstanding, freelancer_id, skill_id) VALUES (0, 5, 10);


/*proposal*/
INSERT INTO proposal (title, description, estimated_time, work_style, status, send_date, confirm_date, freelancer_id, seeker_id) VALUES ('Mejora de la base de datos', 'Buenas.\nTrabajo en Marmol SL y estamos migrando nuestra base de datos pero nuestro equipo no es lo suficientemente grande', '80 días', 'remoteWork', 'waiting', STR_TO_DATE ('18/6/2023', '%d/%m/%Y'), null, 1, 1);
INSERT INTO proposal (title, description, estimated_time, work_style, status, send_date, confirm_date, freelancer_id, seeker_id) VALUES ('Adaptación de la página web', 'Buenas.\nNecesito a alguien para mejorar mi base de datos', '80 días', 'inPersonWork', 'waiting', STR_TO_DATE ('30/5/2023', '%d/%m/%Y'), null, 2, 1);
INSERT INTO proposal (title, description, estimated_time, work_style, status, send_date, confirm_date, freelancer_id, seeker_id) VALUES ('Diseño de página web', 'Buenas.\nNecesito a alguien para mejorar mi base de datos', '80 días', 'hybridWork', 'accepted', STR_TO_DATE ('25/2/2023', '%d/%m/%Y'), STR_TO_DATE ('27/2/2023', '%d/%m/%Y'), 1, 1);
INSERT INTO proposal (title, description, estimated_time, work_style, status, send_date, confirm_date, freelancer_id, seeker_id) VALUES ('Ampliación de equipo', 'Buenas.\nNecesito a alguien para mejorar mi base de datos', '80 días', 'undefined', 'rejected', STR_TO_DATE ('1/3/2021', '%d/%m/%Y'), STR_TO_DATE ('2/3/2021', '%d/%m/%Y'), 1, 1);
INSERT INTO proposal (title, description, estimated_time, work_style, status, send_date, confirm_date, freelancer_id, seeker_id) VALUES ('Creación de página web para mi empresa', 'Buenas.\nNecesito a alguien para mejorar mi base de datos', '80 días', 'undefined', 'accepted', STR_TO_DATE ('1/3/2021', '%d/%m/%Y'), STR_TO_DATE ('2/3/2021', '%d/%m/%Y'), 1, 1);
INSERT INTO proposal (title, description, estimated_time, work_style, status, send_date, confirm_date, freelancer_id, seeker_id) VALUES ('Pequeño proyecto personal', 'Buenas.\nNecesito a alguien para mejorar mi base de datos', '80 días', 'undefined', 'accepted', STR_TO_DATE ('18/8/2022', '%d/%m/%Y'), STR_TO_DATE ('23/8/2022', '%d/%m/%Y'), 1, 1);


/*project*/
INSERT INTO project (name, description, deadline, work_style, status, send_date, start_date, finish_date, signed_by_seeker, signed_by_freelancer, contract_price, freelancer_id, seeker_id)
VALUES ('Actualización', 'Some description', STR_TO_DATE ('31/01/2028', '%d/%m/%Y'), 'remoteWork', 'inprogress', NOW(), NOW(), null, false, false, 1500, 1, 1);
INSERT INTO project (name, description, deadline, work_style, status, send_date, start_date, finish_date, signed_by_seeker, signed_by_freelancer, contract_price, freelancer_id, seeker_id)
VALUES ('Mejora', 'Another description', null, 'inPersonWork', 'preproject', NOW(), null, null, false, true, 0, 1, 1);
INSERT INTO Project (name, description, deadline, work_style, status, send_date, start_date, finish_date, signed_by_seeker, signed_by_freelancer, contract_price, freelancer_id, seeker_id)
VALUES ('New Project', 'New Project Description', STR_TO_DATE ('15/03/2023', '%d/%m/%Y'), 'hybridWork', 'finished', NOW(), NOW(), NOW(), true, true, 800, 1, 1);



/*project_skill*/
INSERT INTO project_skill (id_project, id_skill) VALUES (1, 1);
INSERT INTO project_skill (id_project, id_skill) VALUES (1, 3);
INSERT INTO project_skill (id_project, id_skill) VALUES (2, 1);
INSERT INTO project_skill (id_project, id_skill) VALUES (2, 3);
INSERT INTO project_skill (id_project, id_skill) VALUES (2, 4);
INSERT INTO project_skill (id_project, id_skill) VALUES (2, 5);

/*calification*/
INSERT INTO calification (description, note, project_id) VALUES ('Perfecto', 2, 3);
INSERT INTO calification (description, note, project_id) VALUES ('Casi perfecto', 8, 2);

/*chat*/
INSERT INTO chat (message, send_date, project_id, user_id) VALUES ('Buenos días', STR_TO_DATE ('31/01/2021 10:30:45', '%d/%m/%Y %H:%i:%s'), 3, 4);
INSERT INTO chat (message, send_date, project_id, user_id) VALUES ('Buenos días!', STR_TO_DATE ('31/01/2021 10:32:45', '%d/%m/%Y %H:%i:%s'), 3, 1);
INSERT INTO chat (message, send_date, project_id, user_id) VALUES ('Te añado al grupo de teams', STR_TO_DATE ('31/01/2021 10:40:45', '%d/%m/%Y %H:%i:%s'), 3, 1);
