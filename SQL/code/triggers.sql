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

/* set role "user" to new registered person */
CREATE OR REPLACE FUNCTION set_base_role_to_new_user() RETURNS TRIGGER AS
$set_base_role_to_new_user$
DECLARE
    user_id INTEGER = NEW.id;
BEGIN
    INSERT INTO role_person (person_id, role_id)
    VALUES (user_id, (SELECT id FROM role r WHERE r.name = 'user'));
    RETURN NEW;
END;
$set_base_role_to_new_user$
    LANGUAGE plpgsql;

CREATE TRIGGER set_base_role
    AFTER INSERT
    ON person
    FOR EACH ROW
EXECUTE PROCEDURE set_base_role_to_new_user();

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

-- set is_access to true if user has enough count activity

CREATE OR REPLACE FUNCTION check_access_achievement()
    RETURNS TRIGGER AS
$check_access_achievement$
BEGIN
    if (NEW.is_access = true) THEN
        RETURN NEW;
    end if;
    
    if NEW.completed_count >= (SELECT ach.required_count_activity
                               FROM achievement ach
                               WHERE ach.id = NEW.achievement_id) THEN
        NEW.is_access := true;
    end if;
    RETURN NEW;
END;
$check_access_achievement$
    LANGUAGE plpgsql;

DROP TRIGGER check_access_achievement ON achievement_person;
CREATE TRIGGER check_access_achievement
    BEFORE UPDATE OR INSERT
    on achievement_person
    FOR EACH ROW
EXECUTE function check_access_achievement();