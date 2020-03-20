package br.com.utils

fun <DataSourceDomain: Any, Domain: Any> DataSourceDomain.toDomain(parser: (DataSourceDomain) -> Domain) = parser(this)