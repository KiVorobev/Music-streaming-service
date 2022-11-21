/* create profile with inserting in user table */
CREATE OR REPLACE FUNCTION create_empty_profile() RETURNS TRIGGER AS
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

/* delete audio with deleting author trigger */
CREATE OR REPLACE FUNCTION delete_audio_with_author() RETURNS TRIGGER AS
$$
BEGIN
    IF
        ((SELECT count(*) FROM author_audio WHERE audio_id = OLD.audio_id) = 0)
    THEN
        DELETE
        FROM audio
        WHERE audio.id = OLD.audio_id;
    END IF;
    RETURN old;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER delete_audio_with_author
    AFTER DELETE
    ON author_audio
    FOR EACH ROW
EXECUTE FUNCTION delete_audio_with_author();

/* delete playlist with deleting author */
CREATE OR REPLACE FUNCTION delete_playlist_with_author() RETURNS TRIGGER AS
$$
BEGIN
    IF
        ((SELECT count(*) FROM playlist_author WHERE playlist_id = OLD.playlist_id) = 0)
    THEN
        DELETE
        FROM playlist
        WHERE playlist.id = OLD.playlist_id;
    END IF;
    RETURN old;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER delete_playlist_with_author
    AFTER DELETE
    ON playlist_author
    FOR EACH ROW
EXECUTE FUNCTION delete_playlist_with_author();

/* delete playlist if there is no audio in */
CREATE OR REPLACE FUNCTION delete_playlist_without_audio() RETURNS TRIGGER AS
$$
BEGIN
    IF
        ((SELECT count(*) FROM playlist_audio WHERE playlist_id = OLD.playlist_id) = 0)
    THEN
        DELETE
        FROM playlist
        WHERE playlist.id = OLD.playlist_id;
    END IF;
    RETURN old;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER delete_playlist_without_audio
    AFTER DELETE
    ON playlist_audio
    FOR EACH ROW
EXECUTE FUNCTION delete_playlist_without_audio();