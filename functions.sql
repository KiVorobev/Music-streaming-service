-- function
-- get info about user by username func

drop function get_info_by_username(text);

CREATE OR REPLACE FUNCTION get_info_by_username(_username text)
    RETURNS setof person AS
$$
DECLARE
BEGIN
    RETURN QUERY SELECT *
                 FROM person p
                 WHERE (p.username = _username);
END;
$$
    LANGUAGE plpgsql;

-- get info about audio by audio id
drop function get_audio_info_by_audio_id(INTEGER);

CREATE OR REPLACE FUNCTION get_audio_info_by_audio_id(_audio_id INTEGER)
    RETURNS TABLE
            (
                author_name VARCHAR(32),
                audio_name  VARCHAR(32),
                upload_date TIMESTAMP
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT p.username, a.name, a.upload_date
        FROM author_audio aa
                 JOIN audio a ON a.id = aa.audio_id
                 JOIN person p ON p.id = aa.author_id
        WHERE aa.audio_id = _audio_id;
END;
$$
    LANGUAGE plpgsql;

-- get all audios from author by author name func
drop function get_all_audios_by_author_name(text);

CREATE OR REPLACE FUNCTION get_all_audios_by_author_name(author_name text)
    RETURNS SETOF audio AS
$$
BEGIN
    RETURN QUERY SELECT a.id, a.name, a.text, a.upload_date
                 FROM author_audio aa
                          JOIN person p ON p.id = aa.author_id
                          JOIN audio a ON a.id = aa.audio_id
                 WHERE (p.username = author_name);
END;
$$
    LANGUAGE plpgsql;

-- select all emotions on audio by audio_id func
drop function get_all_emotions_by_audio_id(integer);

CREATE OR REPLACE FUNCTION get_all_emotions_by_audio_id(_audio_id integer)
    RETURNS TABLE
            (
                username_id         INTEGER,
                username            VARCHAR(32),
                emotion_description VARCHAR(32)
            )
AS
$$
BEGIN
    RETURN QUERY SELECT p.id, p.username, e.description
                 FROM person_emotion_audio pea
                          JOIN emotion e ON pea.emotion_id = e.id
                          JOIN person p ON p.id = pea.person_id
                 WHERE pea.audio_id = _audio_id;
END;
$$
    LANGUAGE plpgsql;

-- get amount of nravlicks func
drop function get_amount_of_nravlicks_by_audio_id(integer);

CREATE OR REPLACE FUNCTION get_amount_of_nravlicks_by_audio_id(_audio_id integer)
    RETURNS INTEGER AS
$$
DECLARE
    result INTEGER;
BEGIN
    SELECT count(n.audio_id)
    INTO result
    FROM nravlik n
    WHERE n.audio_id = _audio_id;
    return result;
END;
$$
    LANGUAGE plpgsql;

-- get all posts by profile_id func

CREATE OR REPLACE FUNCTION get_all_posts_by_profile_id(_profile_id INTEGER)
    RETURNS SETOF post AS
$$
BEGIN
    RETURN QUERY
        SELECT *
        FROM post p
        WHERE p.profile_id = _profile_id;
END;
$$
    LANGUAGE plpgsql;

 -- get all comments by post id func
drop function get_comments_by_post_id(INTEGER);

CREATE OR REPLACE FUNCTION get_comments_by_post_id(_post_id INTEGER)
    RETURNS TABLE
            (
                comment_author_username VARCHAR(32),
                comment_text            VARCHAR(2048)
            )
AS
$$
BEGIN
    RETURN QUERY SELECT p.username, comm.text
                 FROM comment comm
                          JOIN person p ON comm.person_id = p.id
                 WHERE comm.post_id = _post_id;
END;
$$
    LANGUAGE plpgsql;

-- get all audios in playlist
drop function get_all_audios_from_playlist_by_playlist_id(INTEGER);

CREATE OR REPLACE FUNCTION get_all_audios_from_playlist_by_playlist_id(_playlist_id INTEGER)
    RETURNS TABLE
            (
                author_name VARCHAR(32),
                audio_name  VARCHAR(32)
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT p.username, a.name
        FROM playlist_audio paud
                 JOIN audio a ON a.id = paud.audio_id
                 JOIN author_audio aa ON aa.audio_id = a.id
                 JOIN person p ON p.id = aa.author_id
        WHERE paud.playlist_id = _playlist_id;
END;
$$
    LANGUAGE plpgsql;

