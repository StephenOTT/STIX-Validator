# STIX-Validator
STIX Validator http server for checking the JSON conformance to the STIX spec

Currently supported schema: STIX 2.0

# Deploy

`java -jar stix-validator-0.5.0.jar`

base url: `localhost:8080`


# Example

POST `localhost:8080/api/validation/validation/sdo/attack-pattern`

JSON Body:

```json
{
  "type": "attack-pattern",
  "id": "attack-pattern--17e6110c-0f51-4d91-8c1c-417d3f886bda",
  "created_by_ref": "identity--826cf0f0-2105-4cf3-a56a-06998d17b1ec",
  "created": "2019-03-13T21:41:01.373Z",
  "modified": "2019-03-13T21:41:01.374Z",
  "revoked": true,
  "labels": [
    "111",
    "crabbing",
    "metricized",
    "potentates",
    "cresylic",
    "ultrasonic"
  ],
  "object_marking_refs": [
    "marking-definition--4805e1e6-f9c8-476c-bae3-1fa8b3a89197",
    "marking-definition--3966fea2-f4a3-4eac-8cb8-c37be13e7fe5",
    "marking-definition--2301e050-08c1-4d68-abe1-372a9c9bf0af"
  ],
  "granular_markings": [
    {
      "marking_ref": "marking-definition--a4cb9815-d20e-41d7-a729-e2f3d432144f",
      "selectors": [
        "tithe"
      ]
    },
    {
      "marking_ref": "marking-definition--d15d48de-abc7-4c3e-a3ba-bad2198478c4",
      "selectors": [
        "cozily",
        "atremble",
        "twaddle",
        "moreish",
        "cruciferous",
        "recommence",
        "bluntly",
        "scudded",
        "quiescent"
      ]
    },
    {
      "marking_ref": "marking-definition--1a6676db-d3f9-403f-8975-3ba1f8f983a6",
      "selectors": [
        "adagio",
        "teff",
        "eburnation",
        "sousaphones",
        "whisks"
      ]
    },
    {
      "marking_ref": "marking-definition--ad10add9-9008-45c7-b40b-0ccb27bb9c27",
      "selectors": [
        "neuk",
        "reuses",
        "squat"
      ]
    }
  ],
  "description": "quicksilver nobbut lame subahs heathfowl slightly bountifulness vitellines creepies custom both boldly darkly unwooded",
  "x_breakfasts": 848823,
  "xx_gats": "respondent persistently trephining anodizes washiest untimely jibe"
}
```

See that `xx_gats` property which should be `x_gats` and the `name` property is missing, which is a required field for a Attack-Pattern.

The result is:

Status: 422 Unprocessable Entity

Body:

```json
{
    "result": "invalid",
    "errors": [
        {
            "type": "attack-pattern",
            "id": "attack-pattern--17e6110c-0f51-4d91-8c1c-417d3f886bda",
            "path": "name",
            "value": null,
            "message": "must not be blank"
        },
        {
            "type": "attack-pattern",
            "id": "attack-pattern--17e6110c-0f51-4d91-8c1c-417d3f886bda",
            "path": "customProperties<K>[xx_gats].<map key>",
            "value": "xx_gats",
            "message": "StartsWith violation: string must start with value: x_, but provided value: xx_gats"
        }
    ]
}
```

If the result is valid, we get:

Status: 200 OK

```json
{
    "result": "valid"
}
```

# API

All Bundleable Objects (SDO, SRO, Data Marking's Marking Definition) and a Bundle itself can be validated through the REST API:

First load will be slower as it is warming up in the JVM.


# Supported Endpoints:

## Bundle

POST `/api/validation/bundle`

# Bundleable Objects

A generic endpoint that supports the validation of all bundleable objects (SDO/Stix Domain objects, Data Markings)

POST `/api/validation/bundleable-object`

## SDO / Stix Domain Objects


### Attack-Pattern

POST `/api/validation/sdo/attack-pattern`

### Campaign

POST `/api/validation/sdo/campaign`

### Course-of-Action

POST `/api/validation/sdo/course-of-action`

### Indicator

POST `/api/validation/sdo/indicator`

### Intrusion-Set

POST `/api/validation/sdo/intrusion-set`

### Malware

POST `/api/validation/sdo/malware`

### Observed-Data

POST `/api/validation/sdo/observed-data`

### Report

POST `/api/validation/sdo/report`

### Threat-Actor

POST `/api/validation/sdo/threat-actor`

### Tool

POST `/api/validation/sdo/tool`

### Vulnerability

POST `/api/validation/sdo/vulnerability`

## SRO / Stix Relationship Objects

### Relationship

POST `/api/validation/sro/relationship`

### Sighting

POST `/api/validation/sro/sighting`

## Data Marking

### Marking Definition

POST `/api/validation/data-marking/marking-definition`


# Build

`mvn clean package`