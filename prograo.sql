INSERT INTO freelancer (name, profile_photo, title, rate, description, registration_date, twitter, facebook, email, linkedin, city, country)
	VALUES ('John Doe', 'profile.jpg', 'Software Developer', 50, 'Experienced software developer', '2022-01-01', 'johndoe', 'johndoe', 'john.doe@email.com', 'johndoe', 'New York', 'USA');
insert into freelancer values (2, "prueba1");
insert into freelancer values (3, "prueba1");
select * from freelancer;
select * from freelancer_skill;
select * from seeker;
select * from user;
select * from project;
select * from proposals;
select id, name, profile_photo, title, city, country, rate from freelancer;
select S.name as skills from freelancer F left join freelancer_skill FS on F.id = FS.freelance_id left join skill S on FS.skill_id = S.id where FS.outstanding = 1;
select avg(C.note) as totalCalification from freelancer F left join project P on F.id = P.freelance_id left join calification C on P.id = C.project_id group by F.id;
select count(C.id) as numberCalification from freelancer F left join project P on F.id = P.freelance_id left join calification C on P.id = C.project_id group by F.id;
select S.name, FS.outstanding, Count(PS.id_project) as numUsed from freelancer_skill FS left join skill S on FS.skill_id = S.id left join project_skill PS on S.id = PS.id_skill where FS.freelance_id = 1 group by FS.skill_id;
select S.id as idSeeker, S.name as nameSeeker, S.profile_photo as profilePhotoSeeker, C.id as idCalification, C.note as noteCalification, C.description as descriptionCalification, C.image as imageCalification, C.date as dateCalification, C.project_id as projectId from calification C left join project P on C.project_id = P.id left join seeker S on P.seeker_id = S.id where P.freelance_id = 1;

SELECT FS.* FROM freelancer F RIGHT JOIN freelancer_skill FS ON F.id = FS.freelancer_id LEFT JOIN skill S ON FS.skill_id = S.id WHERE F.id = 1 AND S.id = 1;
select * from freelancer_skill;
select * from skill;
select S.id, S.name, FS.outstanding from skill S left join freelancer_skill FS on S.id = FS.skill_id left join freelancer F on FS.freelancer_id = F.id;
SELECT P.id AS id, P.title AS title, P.description AS description, P.estimated_time AS estimatedTime, P.work_style AS workStyle, P.status AS status, P.send_date AS sendDate, P.confirm_date AS confirmDate, S.name AS seekerName, F.profile_photo AS profilePhoto
FROM Proposal AS P
INNER JOIN Freelancer AS F ON P.freelancer_id = F.id
INNER JOIN Seeker AS S ON P.seeker_id = S.id WHERE F.id = 1;

SELECT P.id AS id, P.title AS title, P.description AS description, P.estimated_time AS estimatedTime, P.work_style AS workStyle, P.status AS status, P.send_date AS sendDate, P.confirm_date AS confirmDate, U.name AS seekerName, U.profile_photo AS profilePhoto FROM Proposal AS P INNER JOIN Freelancer AS F ON P.freelancer_id = F.id INNER JOIN Seeker AS S ON P.seeker_id = S.id LEFT JOIN User AS U ON S.user_id = U.id;
SELECT
  p.id AS proposalId,
  p.title AS proposalTitle,
  p.description AS proposalDescription,
  p.estimated_time AS proposalEstimatedTime,
  p.work_style AS proposalWorkStyle,
  p.status AS proposalStatus,
  p.send_date AS proposalSendDate,
  p.confirm_date AS proposalConfirmDate,
  s.id AS seekerId,
  su.name AS seekerName,
  su.profile_photo AS seekerProfilePhoto,
  su.title AS seekerTitle,
  l.city AS seekerCity,
  l.country AS seekerCountry,
  f.id AS freelancerId
FROM Proposal p
LEFT JOIN Seeker s ON p.seeker_id = s.id
LEFT JOIN User su ON s.user_id = su.id
LEFT JOIN Freelancer f ON p.freelancer_id = f.id
LEFT JOIN User fu ON f.user_id = fu.id
LEFT JOIN Location l ON su.location_id = l.id
WHERE su.id = 1;

SELECT
  p.id AS proposalId,
  p.title AS proposalTitle,
  p.description AS proposalDescription,
  p.estimated_time AS proposalEstimatedTime,
  p.work_style AS proposalWorkStyle,
  p.status AS proposalStatus,
  p.send_date AS proposalSendDate,
  p.confirm_date AS proposalConfirmDate,
  s.id AS seekerId,
  su.name AS seekerName,
  su.profile_photo AS seekerProfilePhoto,
  su.title AS seekerTitle,
  l.city AS seekerCity,
  l.country AS seekerCountry,
  f.id AS freelancerId
FROM Proposal p
LEFT JOIN Freelancer f ON p.freelancer_id = f.id
LEFT JOIN User fu ON f.user_id = fu.id
LEFT JOIN Seeker s ON p.seeker_id = s.id
LEFT JOIN User su ON s.user_id = su.id
LEFT JOIN Location l ON su.location_id = l.id
WHERE fu.id = 1;

SELECT
  p.id AS proposalId,
  p.title AS proposalTitle,
  p.description AS proposalDescription,
  p.estimated_time AS proposalEstimatedTime,
  p.work_style AS proposalWorkStyle,
  p.status AS proposalStatus,
  p.send_date AS proposalSendDate,
  p.confirm_date AS proposalConfirmDate,
  su.id AS userSeekerId,
  su.name AS seekerName,
  su.profile_photo AS seekerProfilePhoto,
  su.title AS seekerTitle,
  l.city AS seekerCity,
  l.country AS seekerCountry,
  fu.id AS userFreelancerId
FROM Proposal p
LEFT JOIN Freelancer f ON p.freelancer_id = f.id
LEFT JOIN User fu ON f.user_id = fu.id
LEFT JOIN Seeker s ON p.seeker_id = s.id
LEFT JOIN User su ON s.user_id = su.id
LEFT JOIN Location l ON su.location_id = l.id
WHERE fu.id = 1 OR su.id = 1;

SELECT
  p.id AS projectId,
  p.name AS projectTitle,
  p.description AS projectDescription,
  p.estimated_time AS projectEstimatedTime,
  p.work_style AS projectWorkStyle,
  p.status AS projectStatus,
  p.send_date AS projectSendDate,
  p.start_date AS projectStartDate,
  p.finish_date AS projectFinishDate,
  p.daily_num_hours AS projectDailyNumHours,
  su.id AS userSeekerId,
  su.name AS userSeekerName,
  su.profile_photo AS userSeekerProfilePhoto,
  su.title AS userSeekerTitle,
  sl.city AS userSeekerCity,
  sl.country AS userSeekerCountry,
  fu.id AS userFreelancerId,
  fu.name AS userFreelancerName,
  fu.profile_photo AS userFreelancerProfilePhoto,
  fu.title AS userFreelancerTitle,
  fl.city AS userFreelancerCity,
  fl.country AS FreelancerCountry
FROM Project p
LEFT JOIN Freelancer f ON p.freelancer_id = f.id
LEFT JOIN User fu ON f.user_id = fu.id
LEFT JOIN Seeker s ON p.seeker_id = s.id
LEFT JOIN User su ON s.user_id = su.id
LEFT JOIN Location sl ON su.location_id = sl.id
LEFT JOIN Location fl ON fu.location_id = fl.id
WHERE fu.id = 1 OR su.id = 1;

select U.*, F.* from freelancer F left join user U ON U.id = F.user_id;
select * from project;
select * from location;
select * from user;
INSERT INTO user (name, title, profile_photo, password, status, location_id) VALUES ('Luis Sánchez', 'Director de Marmol SL', 'Cara 1.jpg', '12345', 'on', 1);
SELECT * FROM location WHERE id = 1;

SELECT
    U.id AS userId,
    U.name AS userName,
    U.profile_photo AS userProfilePhoto,
    U.title AS userTitle,
    L.city AS city,
    L.country AS country,
    F.id AS freelancerId,
    F.rate AS freelancerRate,
    S.skills AS skills,
    C.totalCalification AS totalCalification,
    C.numberCalification AS numberCalification
FROM
    freelancer F
    JOIN user U ON F.user_id = U.id
    LEFT JOIN location L ON U.location_id = L.id
    LEFT JOIN (
        SELECT
            FS.freelancer_id,
            GROUP_CONCAT(S.name) AS skills
        FROM
            freelancer_skill FS
            JOIN skill S ON FS.skill_id = S.id
        WHERE
            FS.outstanding = true
        GROUP BY
            FS.freelancer_id
    ) S ON F.id = S.freelancer_id
    LEFT JOIN (
        SELECT
            F.id,
            AVG(C.note) AS totalCalification,
            COUNT(C.id) AS numberCalification
        FROM
            freelancer F
            LEFT JOIN project P ON F.id = P.freelancer_id
            LEFT JOIN calification C ON P.id = C.project_id
        GROUP BY
            F.id
    ) C ON F.id = C.id;





SELECT F.id, F.name, F.profile_photo, F.title, F.city, F.country, F.rate, S.skills, C.totalCalification, C.numberCalification FROM freelancer F
LEFT JOIN (
    SELECT FS.freelance_id, GROUP_CONCAT(S.name) AS skills
    FROM freelancer_skill FS
    LEFT JOIN skill S ON FS.skill_id = S.id
    WHERE FS.outstanding = 1
    GROUP BY FS.freelance_id
) S ON F.id = S.freelance_id
LEFT JOIN (
    SELECT 
        F.id,
        AVG(C.note) AS totalCalification,
        COUNT(C.id) AS numberCalification
    FROM freelancer F
    LEFT JOIN project P ON F.id = P.freelance_id
    LEFT JOIN calification C ON P.id = C.project_id
    GROUP BY F.id
) C ON F.id = C.id;

SELECT COUNT(DISTINCT fs.skill_id) AS number_of_skills FROM freelancer f LEFT JOIN freelancer_skill fs ON f.id = fs.freelancer_id WHERE f.id = 3 AND fs.outstanding = 1 GROUP BY f.id;
SELECT IF(COUNT(DISTINCT fs.skill_id) >= 1, 1, 0) AS has_skills FROM freelancer f LEFT JOIN freelancer_skill fs ON f.id = fs.freelancer_id WHERE f.id = 3 AND fs.outstanding = 1 GROUP BY f.id;

SELECT F.id, F.name, F.profile_photo, F.title, F.city, F.country, F.rate, S.skills, C.totalCalification, C.numberCalification FROM freelancer F LEFT JOIN ( SELECT FS.freelance_id, GROUP_CONCAT(S.name) AS skills FROM freelancer_skill FS LEFT JOIN skill S ON FS.skill_id = S.id WHERE FS.outstanding = 1 GROUP BY FS.freelance_id ) S ON F.id = S.freelance_id LEFT JOIN ( SELECT F.id, AVG(C.note) AS totalCalification, COUNT(C.id) AS numberCalification FROM freelancer F LEFT JOIN project P ON F.id = P.freelance_id LEFT JOIN calification C ON P.id = C.project_id GROUP BY F.id ) C ON F.id = C.id;