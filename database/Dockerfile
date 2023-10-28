FROM mysql:8.1.0

# Copy the database schema to the container
COPY ./setup.sql /docker-entrypoint-initdb.d/setup.sql

# Set the empty password for the root user
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

# Set the default database name
ENV MYSQL_DATABASE=r7

# Set the default port
EXPOSE 3306