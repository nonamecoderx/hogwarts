ALTER TABLE student
    ADD CONSTRAINT age_constraint check (age >= 16),
    ADD CONSTRAINT name_unique UNIQUE (name),
ALTER
COLUMN age SET DEFAULT 20;
;

ALTER TABLE faculty
    ADD CONSTRAINT name_unique_color_uniq UNIQUE (name, color)
;