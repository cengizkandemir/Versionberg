/*
 * Copyright (c) 2016 Hieu Rocker
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package io.github.rockerhieu.versionberg

/**
 * Created by rockerhieu on 9/12/16.
 */
class Git {
    static String getCommitSha(String repositoryPath) {
        try {
            return "git --git-dir=${repositoryPath}/.git rev-parse --short HEAD".execute().text.trim()
        } catch (Exception e) {
            Logger.i(e)
            return "null"
        }
    }
    static int getCommitCount(String repositoryPath) {
        try {
            def output = new StringBuilder()
            def error = new StringBuilder()
            def process = "git --git-dir=${repositoryPath}/.git rev-list HEAD --count".execute()
            process.waitForProcessOutput(output, error)
            if (output.isInteger()) {
                return output.toInteger()
            } else {
                Logger.i("Error: ${error}")
            }
        } catch (Exception e) {
            Logger.i(e)
        }
        return 0
    }
}