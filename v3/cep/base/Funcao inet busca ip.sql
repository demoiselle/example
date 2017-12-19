CREATE OR REPLACE FUNCTION public.inet_to_bigint(varchar)
  RETURNS bigint AS
$BODY$
SELECT cast($1 as inet) - inet '0.0.0.0'
$BODY$
  LANGUAGE sql IMMUTABLE STRICT
  COST 100;
ALTER FUNCTION public.inet_to_bigint(varchar)
  OWNER TO postgres;
GRANT EXECUTE ON FUNCTION public.inet_to_bigint(varchar) TO public;
GRANT EXECUTE ON FUNCTION public.inet_to_bigint(varchar) TO postgres;
