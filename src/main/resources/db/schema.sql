CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(60) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT NOW()
);

CREATE TYPE team_status AS ENUM ('open', 'started');

CREATE TABLE team (
    id SERIAL PRIMARY KEY,
    team_code VARCHAR(60) UNIQUE NOT NULL,
    owner_id INTEGER NOT NULL REFERENCES account(id),
    status team_status DEFAULT 'open',
    created TIMESTAMP DEFAULT NOW()
);

CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL REFERENCES team(id),
    name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    edit_token VARCHAR(60) UNIQUE NOT NULL,
    interest_1 VARCHAR(60),
    interest_2 VARCHAR(60),
    interest_3 VARCHAR(60),
    created TIMESTAMP DEFAULT NOW(),
    UNIQUE(team_id, email)
);

CREATE TABLE assignment (
    id SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL REFERENCES team(id),
    santa_id INTEGER NOT NULL REFERENCES member(id),
    receiver_id INTEGER NOT NULL REFERENCES member(id),
    created TIMESTAMP DEFAULT NOW(),
    UNIQUE(team_id, santa_id),
    UNIQUE(team_id, receiver_id),
    CHECK (santa_id <> receiver_id)
);

