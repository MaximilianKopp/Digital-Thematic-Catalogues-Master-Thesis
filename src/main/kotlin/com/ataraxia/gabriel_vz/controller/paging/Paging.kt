package com.ataraxia.gabriel_vz.controller.paging

class Paging(
        var nextEnabled: Boolean,
        var prevEnabled: Boolean,
        var pageSize: Int,
        var pageNumber: Int
) {
    companion object {
        val PAGINATION_STEP = 3
        fun of(totalPages: Int, pageNumber: Int, pageSize: Int): Paging {
            val paging = Paging(
                    pageNumber != totalPages,
                    pageNumber != 1,
                    pageSize,
                    pageNumber
            )

            when {
                totalPages < PAGINATION_STEP * 2 + 6 -> {
                    paging.addPageItems(1, totalPages + 1, pageNumber)
                }
                pageNumber < PAGINATION_STEP * 2 + 1 -> {
                    paging.addPageItems(1, PAGINATION_STEP * 2 + 4, pageNumber)
                    paging.last(totalPages)
                }
                pageNumber > totalPages - PAGINATION_STEP * 2 -> {
                    paging.first(pageNumber)
                    paging.addPageItems(totalPages - PAGINATION_STEP * 2 - 2, totalPages + 1, pageNumber)
                }
                else -> {
                    paging.first(pageNumber)
                    paging.addPageItems(pageNumber - PAGINATION_STEP, pageNumber + PAGINATION_STEP + 1, pageNumber)
                    paging.last(totalPages)
                }
            }

            return paging
        }
    }

    val items: MutableList<PageItem> = mutableListOf()

    fun addPageItems(from: Int, to: Int, pageNumber: Int) {
        for (i in from until to) {
            items.add(
                    PageItem(
                            PageItemType.PAGE,
                            i,
                            pageNumber != i)
            )
        }
    }

    fun last(pageSize: Int) {
        items.add(PageItem(
                PageItemType.DOTS,
                null,
                false
        ))

        items.add(PageItem(
                PageItemType.PAGE,
                pageSize,
                true
        ))
    }

    fun first(pageNumber: Int) {
        items.add(PageItem(
                PageItemType.PAGE,
                1,
                pageNumber != 1
        ))

        items.add(PageItem(
                PageItemType.DOTS,
                null,
                false
        ))
    }
}