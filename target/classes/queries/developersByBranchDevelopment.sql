SELECT developers.id, developers.firstName, developers.lastName, developers.age, developers.sex, developers.salary
FROM developers
INNER JOIN developer_skills
ON developers.id = developer_skills.developerId
INNER JOIN skills
ON developer_skills.skillId = skills.id
WHERE skills.branchDevelopment = ?;
