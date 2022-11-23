/* get info about user by username */
DROP FUNCTION get_info_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_info_by_username(user_name TEXT)
    RETURNS SETOF person AS
$$
DECLARE
BEGIN
    RETURN QUERY SELECT *
                 FROM person p
                 WHERE (p.username = user_name);
END;
$$
    LANGUAGE plpgsql;

/* get amount of nravlicks */
DROP FUNCTION get_amount_of_nravlicks_by_audio_id(INTEGER);

CREATE OR REPLACE FUNCTION get_amount_of_nravlicks_by_audio_id(_audio_id INTEGER)
    RETURNS INTEGER AS
$$
DECLARE
    result INTEGER;
BEGIN
    SELECT count(n.audio_id)
    INTO result
    FROM nravlik n
    WHERE n.audio_id = _audio_id;
    RETURN result;
END;
$$
    LANGUAGE plpgsql;

/* get all audios from author by author name */
DROP FUNCTION get_all_audios_by_author_name(TEXT);

CREATE OR REPLACE FUNCTION get_all_audios_by_author_name(author_name TEXT)
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

/* get all emotions on audio by audio_id */
DROP FUNCTION get_all_emotions_by_audio_id(INTEGER);

CREATE OR REPLACE FUNCTION get_all_emotions_by_audio_id(_audio_id INTEGER)
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

/* get all posts by profile_id */
DROP FUNCTION get_all_posts_by_profile_id(INTEGER);

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

/* get all comments by post id */
DROP FUNCTION get_comments_by_post_id(INTEGER);

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

/* get all audios in playlist */
DROP FUNCTION get_all_audios_from_playlist_by_playlist_id(INTEGER);

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

/* get info about audio by audio id */
DROP FUNCTION get_audio_info_by_audio_id(INTEGER);

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

/* get audio by genre */
DROP FUNCTION get_all_audio_by_genre(TEXT);

CREATE OR REPLACE FUNCTION get_all_audio_by_genre(genre_name TEXT)
    RETURNS SETOF audio AS
$$
BEGIN
    RETURN QUERY SELECT a.id, a.name, a.text, a.upload_date
                 FROM genre_audio ga
                          JOIN genre g ON ga.genre_id = g.id
                          JOIN audio a ON ga.audio_id = a.id
                 WHERE (g.name = genre_name);
END;
$$
    LANGUAGE plpgsql;

/* get achievements by username */
DROP FUNCTION get_achievements_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_achievements_by_username(user_name TEXT)
    RETURNS SETOF achievement AS
$$
BEGIN
    RETURN QUERY SELECT a.id, a.name, a.description, a.reward
                 FROM achievement_person ap
                          JOIN achievement a ON ap.achievement_id = a.id
                          JOIN person p ON ap.person_id = p.id
                 WHERE (p.username = user_name);
END;
$$
    LANGUAGE plpgsql;

/* get save audio by username */
DROP FUNCTION get_all_save_audio_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_all_save_audio_by_username(user_name TEXT)
    RETURNS SETOF audio AS
$$
BEGIN
    RETURN QUERY SELECT a.id, a.name, a.text, a.upload_date
                 FROM save_audio sa
                          JOIN person p ON sa.person_id = p.id
                          JOIN audio a ON sa.audio_id = a.id
                 WHERE (p.username = user_name);
END;
$$
    LANGUAGE plpgsql;

/* get all followers by username */
DROP FUNCTION get_all_followers_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_all_followers_by_username(user_name TEXT)
    RETURNS SETOF person AS
$$
BEGIN
    RETURN QUERY SELECT p.id, p.username
                 FROM person_follow pf
                          JOIN person p ON pf.follower_person_id = p.id OR pf.follow_to_person_id = p.id
                 WHERE (pf.follow_to_person_id = user_name);
END;
$$
    LANGUAGE plpgsql;

/* get subscriptions by username */
DROP FUNCTION get_all_subscriptions_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_all_subscriptions_by_username(user_name TEXT)
    RETURNS SETOF person AS
$$
BEGIN
    RETURN QUERY SELECT p.id, p.username
                 FROM person_follow pf
                          JOIN person p ON pf.follower_person_id = p.id OR pf.follow_to_person_id = p.id
                 WHERE (pf.follower_person_id = user_name);
END;
$$
    LANGUAGE plpgsql;

/* get user balance by username */
DROP FUNCTION get_balance_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_balance_by_username(user_name TEXT)
    RETURNS INTEGER AS
$$
DECLARE
    balance INTEGER;
BEGIN
    SELECT p.balance
    INTO balance
    FROM person p
    WHERE p.username = user_name;
    RETURN balance;
END;
$$
    LANGUAGE plpgsql;

/* get user role by username */
DROP FUNCTION get_role_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_role_by_username(user_name TEXT)
    RETURNS TEXT AS
$$
DECLARE
    returning_role VARCHAR(32);
BEGIN
    SELECT r.name
    INTO returning_role
    FROM role_person rp
             JOIN person p ON p.id = rp.person_id
             JOIN role r ON rp.role_id = r.id
    WHERE p.username = user_name;
    RETURN returning_role;
END;
$$
    LANGUAGE plpgsql;

/* get achievement progress by name and username */
DROP FUNCTION get_achievement_progress_by_name_and_username(TEXT, TEXT);

CREATE OR REPLACE FUNCTION get_achievement_progress_by_name_and_username(_name TEXT, user_name TEXT)
    RETURNS INTEGER AS
$$
BEGIN
    SELECT ap.completed_count
    FROM achievement_person ap
             JOIN person p ON ap.person_id = p.id
             JOIN achievement a ON ap.achievement_id = a.id
    WHERE a.name = _name AND p.username = user_name;
END;
$$
    LANGUAGE plpgsql;