#!/bin/bash
#lwp-request -m GET -e 'http://localhost:8080/v1?format=json' | grep "Content-Type";
#lwp-request -m GET -e 'http://localhost:8080/v1?format=json' | grep "\[ {";
lwp-request -m GET -e 'http://localhost:8080/v1?format=json&day=2018-01-16';
