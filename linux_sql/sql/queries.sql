SELECT
  cpu_number,
  id AS host_id,
  total_mem,
  COUNT(cpu_number) OVER(PARTITION BY cpu_number) AS total_no_of_hosts,
  MAX(total_mem) OVER(
    PARTITION BY cpu_number
    ORDER BY
      total_mem DESC
  ) AS highest_mem
FROM
  host_info;

SELECT
  host_info.id,
  host_info.host_name,
  date_trunc('hour', hu.timestamp) + date_part('minute', hu.timestamp):: int / 5 * interval '5 min' AS rounded_timestamp,
  FLOOR(
    AVG(
      (
        (
          host_info.total_mem - hu.memory_free
        )/ host_info.total_mem
      ) * 100
    )
  ) AS avg_used_mem_percentage
from
  host_info
  INNER JOIN host_usage hu ON host_info.id = hu.host_id
GROUP BY
  rounded_timestamp,
  id;

SELECT
  host_usage.host_id,
  date_trunc('hour', host_usage.timestamp) + date_part('minute', host_usage.timestamp):: int / 5 * interval '5 min' as ts,
  COUNT(*) AS num_data_points
from
  host_usage
GROUP BY
  ts,
  host_id
HAVING
  COUNT(*) < 3
