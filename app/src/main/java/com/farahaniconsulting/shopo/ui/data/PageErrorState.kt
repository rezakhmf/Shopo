package com.farahaniconsulting.shopo.ui.data

import com.farahaniconsulting.shopo.R
import com.farahaniconsulting.shopo.ui.util.text.ResText
import com.farahaniconsulting.shopo.ui.util.text.Text

enum class PageErrorState(val message: Text) {
    NO_NETWORK(ResText(R.string.no_internet_error_msg)),
    SERVER_ERROR(ResText(R.string.generic_server_error)),
}