#!/bin/bash
lwp-request -m GET -e 'http://localhost:8080/v1?format=xml&day=2020-01-16';
lwp-request -m GET -e 'http://localhost:8080/v1?format=json&substitute_id=100';
