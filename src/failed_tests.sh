#!/bin/bash

CSV_FILE="failed_tests.csv"
SLEEP_TIME=600
ITERATIONS=3

# Check if the CSV file exists
if [ ! -f "$CSV_FILE" ]; then
  echo "CSV file '$CSV_FILE' not found."
  exit 1
fi

# Function to run mvn test with parsed tests
run_mvn_test() {
  # Read the CSV file and create a list of tests
  TESTS=()
  while IFS=, read -r line; do
    # Check if the line is not empty
    if [ -n "$line" ]; then
      IFS='#' read -r testClass testMethod <<< "$line"
      # Check if the class and method are not empty
      if [ -n "$testClass" ] && [ -n "$testMethod" ]; then
        TESTS+=("$testClass#$testMethod")
      else
        echo "Invalid format in CSV: $line"
      fi
    fi
  done < "$CSV_FILE"

  # Check if there are tests to run
  if [ ${#TESTS[@]} -eq 0 ]; then
    echo "No tests to run. All tests passed."
    exit
  fi

  # Run mvn test with the parsed tests
  echo "Running mvn test -Dtest="$(IFS=,; echo "${TESTS[*]}")""
  mvn test -Dtest="$(IFS=,; echo "${TESTS[*]}")"
}

# Run the command three times with a 10-second sleep between each iteration
# shellcheck disable=SC2004
for ((i = 1; i <= $ITERATIONS; i++)); do
  echo "Running iteration $i"
  run_mvn_test

  if [ "$i" -lt $ITERATIONS ]; then
    echo "Sleeping for 10 minutes before the next iteration"
    sleep $SLEEP_TIME
#    read -p "Press Enter to exit"
  fi
done
