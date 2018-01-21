SELECT SUM(developers.salary) AS salary
FROM developers
INNER JOIN developer_projects
ON developers.id = developer_projects.developerId
INNER JOIN projects
ON developer_projects.projectId = projects.id
WHERE projects.name = ?;