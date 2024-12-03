INSERT INTO EVENT (ID, START_DATE_TIME, END_DATE_TIME, DESCRIPTION)
VALUES (
           1,
           parsedatetime('2024-12-01 00:00:00', 'yyyy-MM-dd HH:mm:ss'),
           parsedatetime('2024-12-01 23:59:59', 'yyyy-MM-dd HH:mm:ss'),
           '하루종일 지속되는 이벤트'
       ),
       (
           2,
           parsedatetime('2024-11-25 20:00:00', 'yyyy-MM-dd HH:mm:ss'),
           parsedatetime('2024-11-25 21:00:00', 'yyyy-MM-dd HH:mm:ss'),
           '하루 중 일부동안 지속되는 이벤트'
       ),
       (
           3,
           parsedatetime('2024-12-01 00:00:00', 'yyyy-MM-dd HH:mm:ss'),
           parsedatetime('2024-12-05 23:59:59', 'yyyy-MM-dd HH:mm:ss'),
           '5일 동안 지속되는 이벤트'
       ),
       (
           4,
           parsedatetime('2024-12-01 20:00:00', 'yyyy-MM-dd HH:mm:ss'),
           parsedatetime('2024-12-05 21:00:00', 'yyyy-MM-dd HH:mm:ss'),
           '5일 중 일부동안 지속되는 이벤트'
       );
