CREATE TABLE tenants (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE shift_types (
    id UUID PRIMARY KEY,
    uq_name TEXT,
    description TEXT,
    active BOOLEAN,
    time_zone VARCHAR(32),
    tenant_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);

CREATE TABLE shifts (
    id UUID PRIMARY KEY,
    shift_type_id UUID,
    name VARCHAR(128),
    start_date DATE,
    end_date DATE,
    start_time TIME WITHOUT TIME ZONE,
    end_time TIME WITHOUT TIME ZONE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_shift_type FOREIGN KEY (shift_type_id) REFERENCES shift_types(id)
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    user_name VARCHAR(32),
    logged_in SMALLINT DEFAULT 0,
    time_zone VARCHAR(32),
    tenant_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);

CREATE TABLE shift_users (
    id UUID PRIMARY KEY,
    shift_id UUID,
    user_id UUID,
    tenant_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_shift FOREIGN KEY (shift_id) REFERENCES shifts(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);