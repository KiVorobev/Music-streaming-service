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
                audio_name VARCHAR(32),
                authors     TEXT
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT a.name, string_agg(username, ', ') authors
        FROM playlist_audio paud
                 JOIN audio a ON a.id = paud.audio_id
                 JOIN author_audio aa ON aa.audio_id = a.id
                 JOIN person p ON p.id = aa.author_id
        WHERE paud.playlist_id = _playlist_id
        GROUP BY a.name;
END;
$$
    LANGUAGE plpgsql;

/* get info about audio by audio id */
DROP FUNCTION get_audio_info_by_audio_id(INTEGER);

CREATE OR REPLACE FUNCTION get_audio_info_by_audio_id(_audio_id INTEGER)
    RETURNS TABLE
            (
                authors     TEXT,
                audio_name  VARCHAR(32),
                upload_date TIMESTAMP
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT string_agg(p.username, ', '), a.name, a.upload_date
        FROM author_audio aa
                 JOIN audio a ON a.id = aa.audio_id
                 JOIN person p ON p.id = aa.author_id
        WHERE aa.audio_id = _audio_id
        GROUP BY a.name, a.upload_date;
END;
$$
    LANGUAGE plpgsql;

/* get audio by genre */
DROP FUNCTION get_all_audios_by_genre(TEXT);

CREATE OR REPLACE FUNCTION get_all_audios_by_genre(genre_name TEXT)
    RETURNS TABLE
            (
                audio_id          INT,
                audio_name        VARCHAR(32),
                text              VARCHAR(10000),
                audio_upload_date TIMESTAMP,
                authors           TEXT
            )
AS
$$
BEGIN
    RETURN QUERY SELECT a.id, a.name, a.text, a.upload_date, string_agg(p.username, ', ')
                 FROM genre_audio ga
                          JOIN genre g ON ga.genre_id = g.id
                          JOIN audio a ON ga.audio_id = a.id
                          JOIN author_audio aa on a.id = aa.audio_id
                          JOIN person p on p.id = aa.author_id
                 WHERE (g.name = genre_name)
                 GROUP BY a.id;
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
DROP FUNCTION get_roles_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_roles_by_username(user_name TEXT)
    RETURNS TEXT AS
$$
DECLARE
    returning_role TEXT;
BEGIN
    SELECT string_agg(r.name , ', ')
    INTO returning_role
    FROM role_person rp
             JOIN person p ON p.id = rp.person_id
             JOIN role r ON rp.role_id = r.id
    WHERE p.username = user_name
    GROUP BY p.username;
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
    RETURN QUERY
        SELECT ap.completed_count
        FROM achievement_person ap
                 JOIN person p ON ap.person_id = p.id
                 JOIN achievement a ON ap.achievement_id = a.id
        WHERE a.name = _name
          AND p.username = user_name;
END;
$$
    LANGUAGE plpgsql;

/* get audio by name */
DROP FUNCTION get_audio_by_name(TEXT);

CREATE OR REPLACE FUNCTION get_audio_by_name(audio_name TEXT)
    RETURNS SETOF audio AS
$$
BEGIN
    RETURN QUERY
        SELECT *
        FROM audio a
        WHERE a.name ILIKE '%' || audio_name || '%';
END;
$$
    LANGUAGE plpgsql;

/* get playlists by name */
DROP FUNCTION get_playlists_by_name(TEXT);

CREATE OR REPLACE FUNCTION get_playlists_by_name(playlist_name TEXT)
    RETURNS SETOF playlist AS
$$
BEGIN
    RETURN QUERY
        SELECT *
        FROM playlist p
        WHERE p.name ILIKE '%' || playlist_name || '%';
END;
$$
    LANGUAGE plpgsql;

/* get audio by author name */
DROP FUNCTION get_audio_by_author_name(TEXT);

CREATE OR REPLACE FUNCTION get_audio_by_author_name(author_name TEXT)
    RETURNS SETOF audio AS
$$
BEGIN
    RETURN QUERY
        SELECT a.id, a.name, a.text, a.upload_date
        FROM audio a
                 JOIN author_audio aa ON a.id = aa.audio_id
                 JOIN person p ON aa.author_id = p.id
        WHERE p.username ILIKE author_name || '%';
END;
$$
    LANGUAGE plpgsql;

/* get persons by username */
DROP FUNCTION get_persons_by_username(TEXT);

CREATE OR REPLACE FUNCTION get_persons_by_username(user_name TEXT)
    RETURNS SETOF person AS
$$
BEGIN
    RETURN QUERY
        SELECT *
        FROM person p
        WHERE p.username ILIKE user_name || '%';
END;
$$ LANGUAGE plpgsql;

/* follow */
DROP function follow(from_username TEXT, to_username TEXT);

CREATE function follow(from_username TEXT, to_username TEXT)
    RETURNS INTEGER
AS
$$
BEGIN
    INSERT INTO person_follow (follower_person_id, follow_to_person_id)
    VALUES ((SELECT p.id FROM person p where p.username = from_username),
            (SELECT p.id FROM person p where p.username = to_username));
    return 1;
end;
$$
    language plpgsql;

/* unfollow */
DROP function unfollow(from_username TEXT, to_username TEXT);

CREATE function unfollow(from_username TEXT, to_username TEXT)
    RETURNS INTEGER
AS
$$
BEGIN
    DELETE
    FROM person_follow
    WHERE (follower_person_id = (SELECT p.id FROM person p where p.username = from_username)
        AND follow_to_person_id = (SELECT p.id FROM person p where p.username = to_username));
    return 1;
end;
$$
    language plpgsql;

/* create playlist*/
DROP FUNCTION create_playlist(playlist_name text, playlist_description text, _image text, audios text, authors text);

CREATE FUNCTION create_playlist(playlist_name text, playlist_description text, _image text, audios text,
                                authors text) returns playlist
    LANGUAGE plpgsql
as
$$
declare
    res           playlist;
    audios_array  text[] = string_to_array(audios, ',');
    authors_array text[] = string_to_array(authors, ',');
begin
    INSERT INTO playlist (name, description, creation_date, image)
    VALUES (playlist_name, playlist_description, now(), _image)
    RETURNING * INTO res;
    FOR r IN 1..cardinality(authors_array)
        LOOP
            INSERT INTO playlist_author (playlist_id, author_id)
            VALUES (res.id,
                    (SELECT p.id FROM person p WHERE p.username = authors_array[r]));
        END LOOP;
    FOR r IN 1..cardinality(audios_array)
        LOOP
            INSERT INTO playlist_audio (playlist_id, audio_id)
            VALUES (res.id,
                    (SELECT a.id FROM audio a where a.id = audios_array[r]::integer));
        END LOOP;
    RETURN res;
end;
$$;

/* create audio*/
DROP FUNCTION create_new_audio(_name text, _text text, _image text, user_names text, genres text);

CREATE FUNCTION create_new_audio(_name text, _text text, _image text, user_names text, genres text) returns audio
    LANGUAGE plpgsql
as
$$
DECLARE
    res              audio;
    user_names_array text[] = string_to_array(user_names, ',');
    genres_array     text[] = string_to_array(genres, ',');
BEGIN
    INSERT INTO audio(name, text, image, upload_date) VALUES (_name, _text, _image, now()) RETURNING * INTO res;
    FOR r IN 1..cardinality(user_names_array)
        LOOP
            INSERT INTO author_audio (author_id, audio_id)
            VALUES ((SELECT id FROM person WHERE username = user_names_array[r]),
                    res.id);
        END LOOP;
    FOR r IN 1..cardinality(genres_array)
        LOOP
            INSERT INTO genre_audio (genre_id, audio_id)
            VALUES ((SELECT g.id FROM genre g WHERE g.name = genres_array[r]),
                    res.id);
        END LOOP;
    RETURN res;
END;
$$;

/* create comment*/
DROP FUNCTION add_comment(comment_text text, person_name text, _post_id integer);

CREATE FUNCTION add_comment(comment_text text, person_name text, _post_id integer) returns SETOF comment
    LANGUAGE plpgsql
as
$$
BEGIN
    RETURN QUERY INSERT INTO comment (person_id, post_id, text, publication_date)
        VALUES ((SELECT p.id
                 FROM person p
                 WHERE p.username = person_name),
                _post_id, comment_text, now())
        RETURNING *;
end;
$$;

/* create post*/
DROP FUNCTION add_post(_username TEXT, _playlist_id INTEGER, _audio_id INTEGER, _description TEXT);

CREATE FUNCTION add_post(_username text, _playlist_id integer, _audio_id integer, _description text) returns post
    LANGUAGE plpgsql
as
$$
DECLARE
    res post;
begin
    INSERT INTO post (person_id, playlist_id, audio_id, description, publication_date)
    VALUES ((SELECT p.id from person p where p.username = _username),
            _playlist_id,
            _audio_id,
            _description,
            now())
    RETURNING * into res;
    RETURN res;
end;
$$;