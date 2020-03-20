package br.com.repository.helper.exception

import java.lang.Exception

class BusinessException(errorMessage: String?): Exception(errorMessage)