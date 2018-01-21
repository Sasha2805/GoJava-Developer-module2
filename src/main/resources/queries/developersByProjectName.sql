# Вывести писок разработчиков отдельного проекта.

SELECT developers.id, developers.firstName, developers.lastName, developers.age, developers.sex, developers.salary
FROM developers
INNER JOIN developer_projects
ON developers.id = developer_projects.developerId
INNER JOIN projects
ON developer_projects.projectId = projects.id
WHERE projects.name = ?;

