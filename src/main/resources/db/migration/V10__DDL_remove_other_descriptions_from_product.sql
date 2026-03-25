/*
 * changing product table
 * @author Felipe Alves
 * @date 24/03/2026
 */

DO $$
BEGIN

    -- long_description
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name = 'product'
          AND column_name = 'long_description'
    ) THEN
        EXECUTE 'ALTER TABLE ecommerce.product DROP COLUMN long_description';
        RAISE NOTICE 'Coluna long_description removida';
    ELSE
        RAISE NOTICE 'Coluna long_description não encontrada';
    END IF;

    -- short_description
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'ecommerce'
          AND table_name = 'product'
          AND column_name = 'short_description'
    ) THEN
        EXECUTE 'ALTER TABLE ecommerce.product DROP COLUMN short_description';
        RAISE NOTICE 'Coluna short_description removida';
    ELSE
        RAISE NOTICE 'Coluna short_description não encontrada';
    END IF;

END
$$;