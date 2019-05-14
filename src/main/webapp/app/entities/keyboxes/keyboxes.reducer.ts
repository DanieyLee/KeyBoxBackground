import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IKeyboxes, defaultValue } from 'app/shared/model/keyboxes.model';

export const ACTION_TYPES = {
  FETCH_KEYBOXES_LIST: 'keyboxes/FETCH_KEYBOXES_LIST',
  FETCH_KEYBOXES: 'keyboxes/FETCH_KEYBOXES',
  CREATE_KEYBOXES: 'keyboxes/CREATE_KEYBOXES',
  UPDATE_KEYBOXES: 'keyboxes/UPDATE_KEYBOXES',
  DELETE_KEYBOXES: 'keyboxes/DELETE_KEYBOXES',
  RESET: 'keyboxes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IKeyboxes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type KeyboxesState = Readonly<typeof initialState>;

// Reducer

export default (state: KeyboxesState = initialState, action): KeyboxesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_KEYBOXES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_KEYBOXES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_KEYBOXES):
    case REQUEST(ACTION_TYPES.UPDATE_KEYBOXES):
    case REQUEST(ACTION_TYPES.DELETE_KEYBOXES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_KEYBOXES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_KEYBOXES):
    case FAILURE(ACTION_TYPES.CREATE_KEYBOXES):
    case FAILURE(ACTION_TYPES.UPDATE_KEYBOXES):
    case FAILURE(ACTION_TYPES.DELETE_KEYBOXES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_KEYBOXES_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_KEYBOXES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_KEYBOXES):
    case SUCCESS(ACTION_TYPES.UPDATE_KEYBOXES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_KEYBOXES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/keyboxes';

// Actions

export const getEntities: ICrudGetAllAction<IKeyboxes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_KEYBOXES_LIST,
    payload: axios.get<IKeyboxes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IKeyboxes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_KEYBOXES,
    payload: axios.get<IKeyboxes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IKeyboxes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_KEYBOXES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IKeyboxes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_KEYBOXES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IKeyboxes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_KEYBOXES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
