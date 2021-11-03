package com.operations.constant;

public enum NamedQueryEnum implements NamedQuery {
	GET_VEHICLES("GET_VEHICLES"), GET_USERS("GET_USERS");

	private final String namedQueryId;

	private NamedQueryEnum(String namedQueryId) {
		this.namedQueryId = namedQueryId;
	}

	public String getnamedQueryId() {
		return namedQueryId;
	}

}
