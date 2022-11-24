-- indexes

CREATE INDEX audio_name_hash_idw ON audio USING HASH (name);

CREATE INDEX playlist_name_hash_idx ON playlist USING HASH (name);

CREATE INDEX profile_id_idx ON post (profile_id);