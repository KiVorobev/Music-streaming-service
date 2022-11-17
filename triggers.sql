-- create profile with inserting in user table
drop trigger create_empty_profile on person;

CREATE OR REPLACE function create_empty_profile() RETURNS TRIGGER AS
$create_empty_profile$
DECLARE
    user_id INTEGER = NEW.id;
BEGIN
    INSERT INTO profile (person_id)
    VALUES (user_id);
    RETURN NEW;
END;
$create_empty_profile$
    LANGUAGE plpgsql;

CREATE TRIGGER create_empty_profile
    AFTER INSERT
    ON person
    FOR EACH ROW
EXECUTE PROCEDURE create_empty_profile();

-- delete audio with deleting author trigger
CREATE OR REPLACE function delete_audio_with_author() returns trigger AS
$$
begin
    if ((SELECT count(*) FROM author_audio WHERE audio_id = OLD.audio_id) = 0)
    then
        DELETE FROM audio WHERE audio.id = OLD.audio_id;
    end if;
    return old;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_audio_with_author
    after DELETE
    ON author_audio
    for each row
execute function delete_audio_with_author();

-- delete playlist with deleting author
CREATE OR REPLACE function delete_playlist_with_author() returns trigger AS
$$
begin
    if ((SELECT count(*) FROM playlist_author WHERE playlist_id = OLD.playlist_id) = 0)
    then
        DELETE FROM playlist WHERE playlist.id = OLD.playlist_id;
    end if;
    return old;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_playlist_with_author
    after DELETE
    ON playlist_author
    for each row
execute function delete_playlist_with_author();

-- delete playlist if there is no audio in
CREATE OR REPLACE function delete_playlist_without_audio() returns trigger AS
$$
begin
    if ((SELECT count(*) FROM playlist_audio WHERE playlist_id = OLD.playlist_id) = 0)
    then
        DELETE FROM playlist WHERE playlist.id = OLD.playlist_id;
    end if;
    return old;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_playlist_without_audio
    after DELETE
    ON playlist_audio
    for each row
execute function delete_playlist_without_audio();