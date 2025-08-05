@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  bd_trebajo startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and BD_TREBAJO_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\bd_trebajo.jar;%APP_HOME%\lib\javalin-bundle-4.6.4.jar;%APP_HOME%\lib\javalin-openapi-4.6.4.jar;%APP_HOME%\lib\swagger-parser-2.0.27.jar;%APP_HOME%\lib\swagger-parser-v2-converter-2.0.27.jar;%APP_HOME%\lib\swagger-parser-v3-2.0.27.jar;%APP_HOME%\lib\swagger-parser-core-2.0.27.jar;%APP_HOME%\lib\kotlin-openapi3-dsl-1.0.0.jar;%APP_HOME%\lib\swagger-core-2.1.10.jar;%APP_HOME%\lib\swagger-models-2.1.10.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.15.2.jar;%APP_HOME%\lib\jackson-module-kotlin-2.15.2.jar;%APP_HOME%\lib\jackson-module-jsonSchema-2.15.2.jar;%APP_HOME%\lib\swagger-compat-spec-parser-1.0.55.jar;%APP_HOME%\lib\swagger-parser-1.0.55.jar;%APP_HOME%\lib\swagger-core-1.6.2.jar;%APP_HOME%\lib\swagger-models-1.6.2.jar;%APP_HOME%\lib\jackson-annotations-2.15.2.jar;%APP_HOME%\lib\jackson-datatype-json-org-2.15.2.jar;%APP_HOME%\lib\openapi-parser-4.0.4.jar;%APP_HOME%\lib\jsonoverlay-4.0.4.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.15.2.jar;%APP_HOME%\lib\jackson-core-2.15.2.jar;%APP_HOME%\lib\json-patch-1.13.jar;%APP_HOME%\lib\json-schema-validator-2.2.14.jar;%APP_HOME%\lib\json-schema-core-1.2.14.jar;%APP_HOME%\lib\jackson-coreutils-equivalence-1.0.jar;%APP_HOME%\lib\jackson-coreutils-2.0.jar;%APP_HOME%\lib\jackson-databind-2.15.2.jar;%APP_HOME%\lib\slf4j-simple-2.0.7.jar;%APP_HOME%\lib\mysql-connector-java-8.0.28.jar;%APP_HOME%\lib\javalin-testtools-4.6.4.jar;%APP_HOME%\lib\javalin-4.6.4.jar;%APP_HOME%\lib\http2-server-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-alpn-conscrypt-server-9.4.48.v20220622.jar;%APP_HOME%\lib\logback-classic-1.2.11.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.5.32.jar;%APP_HOME%\lib\slf4j-api-2.0.7.jar;%APP_HOME%\lib\protobuf-java-3.11.4.jar;%APP_HOME%\lib\jetty-alpn-server-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-webapp-9.4.48.v20220622.jar;%APP_HOME%\lib\websocket-server-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-servlet-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-security-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-server-9.4.48.v20220622.jar;%APP_HOME%\lib\redoc-2.0.0-rc.45.jar;%APP_HOME%\lib\swagger-ui-4.10.3.jar;%APP_HOME%\lib\classgraph-4.8.102.jar;%APP_HOME%\lib\okhttp-4.9.1.jar;%APP_HOME%\lib\kotlin-reflect-1.5.32.jar;%APP_HOME%\lib\http2-common-9.4.48.v20220622.jar;%APP_HOME%\lib\conscrypt-openjdk-uber-2.5.2.jar;%APP_HOME%\lib\http2-hpack-9.4.48.v20220622.jar;%APP_HOME%\lib\websocket-client-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-client-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-http-9.4.48.v20220622.jar;%APP_HOME%\lib\websocket-common-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-io-9.4.48.v20220622.jar;%APP_HOME%\lib\logback-core-1.2.11.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.5.32.jar;%APP_HOME%\lib\okio-jvm-2.8.0.jar;%APP_HOME%\lib\kotlin-stdlib-1.5.32.jar;%APP_HOME%\lib\websocket-servlet-9.4.48.v20220622.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\jetty-xml-9.4.48.v20220622.jar;%APP_HOME%\lib\commons-io-2.6.jar;%APP_HOME%\lib\jetty-util-ajax-9.4.48.v20220622.jar;%APP_HOME%\lib\jetty-util-9.4.48.v20220622.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.5.32.jar;%APP_HOME%\lib\websocket-api-9.4.48.v20220622.jar;%APP_HOME%\lib\javax.mail-api-1.6.1.jar;%APP_HOME%\lib\javax.mail-1.6.1.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\json-20230227.jar;%APP_HOME%\lib\commons-lang3-3.7.jar;%APP_HOME%\lib\uri-template-0.10.jar;%APP_HOME%\lib\guava-28.2-android.jar;%APP_HOME%\lib\httpclient-4.5.2.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.2.jar;%APP_HOME%\lib\swagger-annotations-2.1.10.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\snakeyaml-2.0.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\swagger-annotations-1.6.2.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\msg-simple-1.2.jar;%APP_HOME%\lib\btf-1.3.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-compat-qual-2.5.5.jar;%APP_HOME%\lib\error_prone_annotations-2.3.4.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\mailapi-1.6.2.jar;%APP_HOME%\lib\joda-time-2.10.5.jar;%APP_HOME%\lib\libphonenumber-8.11.1.jar;%APP_HOME%\lib\jopt-simple-5.0.4.jar;%APP_HOME%\lib\httpcore-4.4.4.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.9.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.1.jar;%APP_HOME%\lib\rhino-1.7.7.2.jar


@rem Execute bd_trebajo
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %BD_TREBAJO_OPTS%  -classpath "%CLASSPATH%" com.trukea.Main %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable BD_TREBAJO_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%BD_TREBAJO_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
