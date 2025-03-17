ALTER TABLE budget
ALTER COLUMN period_begin SET DEFAULT CURRENT_TIMESTAMP;
CREATE OR REPLACE FUNCTION set_default_period_end()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.period_end IS NULL THEN
        NEW.period_end := NEW.period_begin + INTERVAL '7 days';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_set_period_end
    BEFORE INSERT ON budget
    FOR EACH ROW
EXECUTE FUNCTION set_default_period_end();


