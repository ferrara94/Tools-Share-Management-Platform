#!/bin/bash

# Wait for the Postgres container to be ready
echo "Waiting for Postgres container to be ready..."
until sudo docker exec postgres-sql-tsmp pg_isready -U username > /dev/null 2>&1; do
  echo "Postgres is not ready yet, waiting..."
  sleep 2
done

# ------------------------------------------------------------------
# TOOL TABLE
# ------------------------------------------------------------------


echo "Inserting a tool - Voltage Tester - Owner Mark"
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"tool\" (
  id,
  created_by,
  created_date,
  last_modified_by,
  last_modified_date,
  name,
  description,
  condition,
  category,
  tool_picture,
  available,
  archived
)
VALUES (
  1,
  1,
  NOW(),
  NULL,
  NULL,
  'Voltage Tester',
  'A tool used for testing electrical voltage.',
  'New',
  'Electrical',
  'VoltageTester',
  true,
  false
);
"

echo "Inserting a tool - Claw Hammer - Owner Mark"
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"tool\" (
  id,
  created_by,
  created_date,
  last_modified_by,
  last_modified_date,
  name,
  description,
  condition,
  category,
  tool_picture,
  available,
  archived
)
VALUES (
  2,
  1,
  NOW(),
  NULL,
  NULL,
  'Claw Hammer',
  'A hammer with a curved claw for removing nails.',
  'Good state',
  'Carpentry',
  'ClawHammer',
  true,
  false
);
"

echo "Inserting a tool - Soldering Iron - Owner Mark"
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"tool\" (
  id,
  created_by,
  created_date,
  last_modified_by,
  last_modified_date,
  name,
  description,
  condition,
  category,
  tool_picture,
  available,
  archived
)
VALUES (
  3,
  1,
  NOW(),
  NULL,
  NULL,
  'Soldering Iron',
  'A tool for melting solder to join electrical components.',
  'Good state',
  'Electrical',
  'SolderingIron',
  true,
  false
);
"

echo "Inserting a tool - Utility Knife - Owner Mark"
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"tool\" (
  id,
  created_by,
  created_date,
  last_modified_by,
  last_modified_date,
  name,
  description,
  condition,
  category,
  tool_picture,
  available,
  archived
)
VALUES (
  4,
  1,
  NOW(),
  NULL,
  NULL,
  'Utility Knife',
  'A knife with a retractable blade used for general cutting tasks.',
  'New',
  'General',
  'UtilityKnife',
  true,
  false
);
"

echo "Inserting a tool - Measuring Tape - Owner Mark"
sudo docker exec postgres-sql-tsmp psql -U username -d tool_share_management_platform -c "
INSERT INTO \"tool\" (
  id,
  created_by,
  created_date,
  last_modified_by,
  last_modified_date,
  name,
  description,
  condition,
  category,
  tool_picture,
  available,
  archived
)
VALUES (
  5,
  1,
  NOW(),
  NULL,
  NULL,
  'Measuring Tape',
  'A flexible ruler used to measure size or distance.',
  'Normal',
  'Measuring',
  'MeasuringTape',
  true,
  false
);
"



echo "User and tools inserted successfully."