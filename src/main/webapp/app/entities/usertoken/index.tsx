import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Usertoken from './usertoken';
import UsertokenDetail from './usertoken-detail';
import UsertokenUpdate from './usertoken-update';
import UsertokenDeleteDialog from './usertoken-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UsertokenUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UsertokenUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UsertokenDetail} />
      <ErrorBoundaryRoute path={match.url} component={Usertoken} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={UsertokenDeleteDialog} />
  </>
);

export default Routes;
