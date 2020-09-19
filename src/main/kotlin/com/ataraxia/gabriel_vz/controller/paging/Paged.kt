package com.ataraxia.gabriel_vz.controller.paging
import org.springframework.data.domain.Page

class Paged<T>(
         var page: Page<T>,
         var paging: Paging
)