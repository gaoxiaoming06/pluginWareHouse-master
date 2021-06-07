package com.michael.pluginwarehouse

import android.text.TextUtils
import androidx.collection.SimpleArrayMap
import java.util.*
import java.util.regex.Pattern

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/08/02
 * desc  : utils about regex
</pre> *
 */
class RegexUtils private constructor() {
    object Regex {
        /**
         * Regex of simple mobile.
         */
        const val REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$"

        /**
         * Regex of exact mobile.
         *
         * china mobile: 134(0-8), 135, 136, 137, 138, 139, 147, 150, 151, 152, 157, 158, 159, 178, 182, 183, 184, 187, 188, 198
         *
         * china unicom: 130, 131, 132, 145, 155, 156, 166, 171, 175, 176, 185, 186
         *
         * china telecom: 133, 153, 173, 177, 180, 181, 189, 199
         *
         * global star: 1349
         *
         * virtual operator: 170
         */
        const val REGEX_MOBILE_EXACT =
            "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$"

        /**
         * Regex of telephone number.
         */
        const val REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}"

        /**
         * Regex of id card number which length is 15.
         */
        const val REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"

        /**
         * Regex of id card number which length is 18.
         */
        const val REGEX_ID_CARD18 =
            "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"

        /**
         * Regex of email.
         */
        const val REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"

        /**
         * Regex of url.
         */
        const val REGEX_URL = "[a-zA-z]+://[^\\s]*"

        /**
         * Regex of Chinese character.
         */
        const val REGEX_ZH = "^[\\u4e00-\\u9fa5]+$"

        /**
         * Regex of username.
         *
         * scope for "a-z", "A-Z", "0-9", "_", "Chinese character"
         *
         * can't end with "_"
         *
         * length is between 6 to 20
         */
        const val REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$"

        /**
         * Regex of date which pattern is "yyyy-MM-dd".
         */
        const val REGEX_DATE =
            "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"

        /**
         * Regex of ip address.
         */
        const val REGEX_IP =
            "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)"
    }

    companion object {
        private val CITY_MAP = SimpleArrayMap<String, String?>()

        /**
         * Return whether input matches regex of simple mobile.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isMobileSimple(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_MOBILE_SIMPLE, input)
        }
        ///////////////////////////////////////////////////////////////////////////
        // If u want more please visit http://toutiao.com/i6231678548520731137
        ///////////////////////////////////////////////////////////////////////////
        /**
         * Return whether input matches regex of exact mobile.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isMobileExact(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_MOBILE_EXACT, input)
        }

        /**
         * Return whether input matches regex of telephone number.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isTel(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_TEL, input)
        }

        /**
         * Return whether input matches regex of id card number which length is 15.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isIDCard15(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_ID_CARD15, input)
        }

        /**
         * Return whether input matches regex of id card number which length is 18.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isIDCard18(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_ID_CARD18, input)
        }

        /**
         * Return whether input matches regex of exact id card number which length is 18.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isIDCard18Exact(input: CharSequence): Boolean {
            if (isIDCard18(input)) {
                val factor = intArrayOf(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2)
                val suffix = charArrayOf('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2')
                if (CITY_MAP.isEmpty) {
                    CITY_MAP.put("11", "北京")
                    CITY_MAP.put("12", "天津")
                    CITY_MAP.put("13", "河北")
                    CITY_MAP.put("14", "山西")
                    CITY_MAP.put("15", "内蒙古")
                    CITY_MAP.put("21", "辽宁")
                    CITY_MAP.put("22", "吉林")
                    CITY_MAP.put("23", "黑龙江")
                    CITY_MAP.put("31", "上海")
                    CITY_MAP.put("32", "江苏")
                    CITY_MAP.put("33", "浙江")
                    CITY_MAP.put("34", "安徽")
                    CITY_MAP.put("35", "福建")
                    CITY_MAP.put("36", "江西")
                    CITY_MAP.put("37", "山东")
                    CITY_MAP.put("41", "河南")
                    CITY_MAP.put("42", "湖北")
                    CITY_MAP.put("43", "湖南")
                    CITY_MAP.put("44", "广东")
                    CITY_MAP.put("45", "广西")
                    CITY_MAP.put("46", "海南")
                    CITY_MAP.put("50", "重庆")
                    CITY_MAP.put("51", "四川")
                    CITY_MAP.put("52", "贵州")
                    CITY_MAP.put("53", "云南")
                    CITY_MAP.put("54", "西藏")
                    CITY_MAP.put("61", "陕西")
                    CITY_MAP.put("62", "甘肃")
                    CITY_MAP.put("63", "青海")
                    CITY_MAP.put("64", "宁夏")
                    CITY_MAP.put("65", "新疆")
                    CITY_MAP.put("71", "台湾")
                    CITY_MAP.put("81", "香港")
                    CITY_MAP.put("82", "澳门")
                    CITY_MAP.put("91", "国外")
                }
                if (CITY_MAP[input.subSequence(0, 2).toString()] != null) {
                    var weightSum = 0
                    for (i in 0..16) {
                        weightSum += (input[i] - '0') * factor[i]
                    }
                    val idCardMod = weightSum % 11
                    val idCardLast = input[17]
                    return idCardLast == suffix[idCardMod]
                }
            }
            return false
        }

        /**
         * Return whether input matches regex of email.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isEmail(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_EMAIL, input)
        }

        /**
         * Return whether input matches regex of url.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isURL(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_URL, input)
        }

        /**
         * Return whether input matches regex of Chinese character.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isZh(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_ZH, input)
        }

        /**
         * Return whether input matches regex of username.
         *
         * scope for "a-z", "A-Z", "0-9", "_", "Chinese character"
         *
         * can't end with "_"
         *
         * length is between 6 to 20.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isUsername(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_USERNAME, input)
        }

        /**
         * Return whether input matches regex of date which pattern is "yyyy-MM-dd".
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isDate(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_DATE, input)
        }

        /**
         * Return whether input matches regex of ip address.
         *
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isIP(input: CharSequence?): Boolean {
            return isMatch(Regex.REGEX_IP, input)
        }

        /**
         * Return whether input matches the regex.
         *
         * @param regex The regex.
         * @param input The input.
         * @return `true`: yes<br></br>`false`: no
         */
        fun isMatch(regex: String?, input: CharSequence?): Boolean {
            return input != null && input.length > 0 && Pattern.matches(regex, input)
        }

        /**
         * Return the list of input matches the regex.
         *
         * @param regex The regex.
         * @param input The input.
         * @return the list of input matches the regex
         */
        fun getMatches(regex: String?, input: CharSequence?): List<String> {
            if (input == null) return emptyList()
            val matches: MutableList<String> = ArrayList()
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(input)
            while (matcher.find()) {
                matches.add(matcher.group())
            }
            return matches
        }

        /**
         * Splits input around matches of the regex.
         *
         * @param input The input.
         * @param regex The regex.
         * @return the array of strings computed by splitting input around matches of regex
         */
        fun getSplits(input: String?, regex: String?): Array<String?> {
            return input?.split(regex!!)?.toTypedArray() ?: arrayOfNulls(0)
        }

        /**
         * Replace the first subsequence of the input sequence that matches the
         * regex with the given replacement string.
         *
         * @param input       The input.
         * @param regex       The regex.
         * @param replacement The replacement string.
         * @return the string constructed by replacing the first matching
         * subsequence by the replacement string, substituting captured
         * subsequences as needed
         */
        fun getReplaceFirst(
            input: String?,
            regex: String?,
            replacement: String?
        ): String {
            return if (input == null) "" else Pattern.compile(regex).matcher(input)
                .replaceFirst(replacement)
        }

        /**
         * Replace every subsequence of the input sequence that matches the
         * pattern with the given replacement string.
         *
         * @param input       The input.
         * @param regex       The regex.
         * @param replacement The replacement string.
         * @return the string constructed by replacing each matching subsequence
         * by the replacement string, substituting captured subsequences
         * as needed
         */
        fun getReplaceAll(
            input: String?,
            regex: String?,
            replacement: String?
        ): String {
            return if (input == null) "" else Pattern.compile(regex).matcher(input)
                .replaceAll(replacement)
        }

        fun isName(name: String?): Boolean {
            if (TextUtils.isEmpty(name)) {
                return false
            }
            //因项目需求，只需要限定在中文和英文上即可，长度已经在Android EditText中限制输入，此处不做长度限制
            val p = Pattern.compile("^[\u4E00-\u9FA5a-zA-Z]+")
            val m = p.matcher(name)
            return m.matches()
        }

        fun isWechatId(wechatId: String?): Boolean {
            // 校验微信号正则
            val judge = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$"
            val pat = Pattern.compile(judge)
            val mat = pat.matcher(wechatId)
            return mat.matches()
        }
    }

    init {
//        throw UnsupportedOperationException("u can't instantiate me...")
    }
}